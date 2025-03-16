package forza.telemetry.data.models

import forza.telemetry.data.TelemetryData

data class SuspensionModel(
    val normalizedSuspensionTravelFrontLeft: Float = 0f,
    val normalizedSuspensionTravelFrontRight: Float = 0f,
    val normalizedSuspensionTravelRearLeft: Float = 0f,
    val normalizedSuspensionTravelRearRight: Float = 0f,
    val suspensionTravelMetersFrontLeft: Float = 0f,
    val suspensionTravelMetersFrontRight: Float = 0f,
    val suspensionTravelMetersRearLeft: Float = 0f,
    val suspensionTravelMetersRearRight: Float = 0f
) {
    companion object {
        fun fromTelemetryData(data: TelemetryData?): SuspensionModel? {
            if (data == null) {
                return SuspensionModel()
            }
            return SuspensionModel(
                normalizedSuspensionTravelFrontLeft = data.normalizedSuspensionTravelFrontLeft,
                normalizedSuspensionTravelFrontRight = data.normalizedSuspensionTravelFrontRight,
                normalizedSuspensionTravelRearLeft = data.normalizedSuspensionTravelRearLeft,
                normalizedSuspensionTravelRearRight = data.normalizedSuspensionTravelRearRight,
                suspensionTravelMetersFrontLeft = data.suspensionTravelMetersFrontLeft,
                suspensionTravelMetersFrontRight = data.suspensionTravelMetersFrontRight,
                suspensionTravelMetersRearLeft = data.suspensionTravelMetersRearLeft,
                suspensionTravelMetersRearRight = data.suspensionTravelMetersRearRight,
            )
        }
    }
}