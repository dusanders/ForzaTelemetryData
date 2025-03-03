package forza.telemetry.data.types

import forza.telemetry.data.TelemetryData

interface ForzaUdpSocketEvents {
  fun onSocketError(e: Exception)
  fun onData(data: TelemetryData)
  fun onOpen(port: Int)
}