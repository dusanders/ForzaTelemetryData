# ForzaTelemetryData
Forza telemetry parser and udp listener for getting telemetry data from the Forza Horizon / Motorsport games.

This module opens a UDP socket and listens for and parses Forza data packets. Tested against FM 7 and Motorsport (2023) but should also work with Horizon 4 & 5.

UDP socket and listening logic is handled on its own Thread within the `ForzaUdpSocket.kt` file.

## Importing into project

Clone repo, and adjust gradle files to include the module:

Example project-level settings.gradle.kts

```gradle
.
.
.
rootProject.name = "ForzaUtils"
include(":app", ":data")
project(":data").projectDir = file("../ForzaTelemetryData/data")

```

Example app-level build.gradle.kts

```gradle
dependencies {
    .
    .
    .

    implementation(project(":data"))
    .
    .
    .
}
```

## Usage

Usage requires the app's Context instance to access SQLite database that holds track and car info for Motorsport (2023). Unfortunately, that is the only database created at the moment.

`ForzaUdpSocket.kt` requires a callback object matching interface `ForzaUdpSocketEvents.kt` that has methods for socket error, data, and open events

The module will begin listening once the `bind()` method is called.

Example usage (wrapped into Service class usable from MainActivity or such):

```kotlin
class ForzaService(context: Context) {
  private var port = 5400
  private var forzaUdpSocket: ForzaUdpSocket
  // Data state
  private val _data: MutableLiveData<TelemetryData?> = MutableLiveData()
  val data: LiveData<TelemetryData?> get() = _data

  private val forzaSocketEvent = object : ForzaUdpSocketEvents {
    override fun onSocketError(e: Exception) {
      Log.e("ForzaService", "Socket Error: ${e.message}")
    }

    override fun onData(data: TelemetryData) {
      _data.postValue(data)
    }

    override fun onOpen(port: Int) {
      Log.d("ForzaService", "UDP Socket open!")
    }
  }

  /**
  * Initialize the module
  */
  init {
    forzaUdpSocket = ForzaUdpSocket(context, forzaSocketEvent)
  }

  /**
  * Start the module
  */
  fun start() {
    forzaUdpSocket.bind(port)
  }

  /**
   * Stops the Forza listener
   */
  fun stop() {
    Log.w(_tag, "Stopping Forza listener...")
    forzaUdpSocket.stop()
  }
}
```

## Data

Basic `TelemetryData` class is created for every UDP packet received. This contains all the data as parsed from the byte array. 

[TelemetryData](https://github.com/dusanders/ForzaTelemetryData/blob/main/data/src/main/java/forza/telemetry/data/TelemetryData.kt)

Helper models are available that group data points into logical sets: 

[Data Models](https://github.com/dusanders/ForzaTelemetryData/tree/main/data/src/main/java/forza/telemetry/data/models)

Example:

```kotlin

fun onData(data: TelemetryData?){
  val tireData = TireModel.fromTelemetryData(data)
  val leftFrontTireTemp = tireData.tireTempFrontLeft

  val worldInfo = SpatialModel.fromTelemetryData(data)
  val positionX = worldInfo.positionX
  val angularVelocityX = worldInfo.angularVelocityX

  val raceInfo = RaceModel.fromTelemetryData(data)
  val racePosition = raceInfo.position
  val laps = raceInfo.lapNumber
}

```

## CarModel and TrackModel

These models are a combination of telemetry data such as track id and car speed, throttle, brake, etc. However, they also attempt to parse game values for Horizon and Motorsport car class, car group, etc. Motorsport (2023) uses a SQLite database lookup while Horizon uses some hard-coded strings. 

If values are not found or not parsed, a string value of `-` is returned or used as a placeholder.

See [TrackModel](https://github.com/dusanders/ForzaTelemetryData/blob/main/data/src/main/java/forza/telemetry/data/models/TrackModel.kt) as an example of default values. Currently, only Motorsport (2023) is included as a database.