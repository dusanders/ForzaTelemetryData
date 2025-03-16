package forza.telemetry.data.models

import forza.telemetry.data.ForzaConstants
import forza.telemetry.data.TelemetryData
import forza.telemetry.data.database.FM8DatabaseService
import forza.telemetry.data.database.FM8TrackInfo

class TrackModel
internal constructor(
  val data: TelemetryData,
  fM8DatabaseService: FM8DatabaseService
) {
  val id = data.trackID

  private var fm8TrackInfo: FM8TrackInfo? = null

  init {
    if (data.gameVersion == ForzaConstants.GameVersion.MOTORSPORT_8) {
      fm8TrackInfo = fM8DatabaseService.getFM8TrackInfo(data.trackID)
    }
  }

  fun getTrack(): String {
    return fm8TrackInfo?.track ?: "-"
  }

  fun getCircuit(): String {
    return fm8TrackInfo?.circuit ?: "-"
  }

  fun getLengthKm(): Float {
    return fm8TrackInfo?.lengthKm ?: 0F
  }

  fun getLocation(): String {
    return fm8TrackInfo?.location ?: "-"
  }

  fun getIocCode(): String {
    return fm8TrackInfo?.iocCode ?: "-"
  }
}