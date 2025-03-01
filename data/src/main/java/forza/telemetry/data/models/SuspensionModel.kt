package forza.telemetry.data.models

import forza.telemetry.data.TelemetryData

class SuspensionModel(val data: TelemetryData) {
    val normalizedSuspensionTravelFrontLeft = data.normalizedSuspensionTravelFrontLeft
    val normalizedSuspensionTravelFrontRight = data.normalizedSuspensionTravelFrontRight
    val normalizedSuspensionTravelRearLeft = data.normalizedSuspensionTravelRearLeft
    val normalizedSuspensionTravelRearRight = data.normalizedSuspensionTravelRearRight
    val suspensionTravelMetersFrontLeft = data.suspensionTravelMetersFrontLeft
    val suspensionTravelMetersFrontRight = data.suspensionTravelMetersFrontRight
    val suspensionTravelMetersRearLeft = data.suspensionTravelMetersRearLeft
    val suspensionTravelMetersRearRight = data.suspensionTravelMetersRearRight
}