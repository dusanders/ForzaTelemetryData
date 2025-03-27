package forza.telemetry.data.models

import forza.telemetry.data.TelemetryData

data class TireModel(
    val steerAngle: Float = 0f,
    val tireSlipRatioFrontLeft: Float = 0f,
    val tireSlipRatioFrontRight: Float = 0f,
    val tireSlipRatioRearLeft: Float = 0f,
    val tireSlipRatioRearRight: Float = 0f,
    val wheelRotationSpeedFrontLeft: Float = 0f,
    val wheelRotationSpeedFrontRight: Float = 0f,
    val wheelRotationSpeedRearLeft: Float = 0f,
    val wheelRotationSpeedRearRight: Float = 0f,
    val wheelOnRumbleStripFrontLeft: Boolean = false,
    val wheelOnRumbleStripFrontRight: Boolean = false,
    val wheelOnRumbleStripRearLeft: Boolean = false,
    val wheelOnRumbleStripRearRight: Boolean = false,
    val wheelInPuddleDepthFrontLeft: Float = 0f,
    val wheelInPuddleDepthFrontRight: Float = 0f,
    val wheelInPuddleDepthRearLeft: Float = 0f,
    val wheelInPuddleDepthRearRight: Float = 0f,
    val surfaceRumbleFrontLeft: Float = 0f,
    val surfaceRumbleFrontRight: Float = 0f,
    val surfaceRumbleRearLeft: Float = 0f,
    val surfaceRumbleRearRight: Float = 0f,
    val tireSlipAngleFrontLeft: Float = 0f,
    val tireSlipAngleFrontRight: Float = 0f,
    val tireSlipAngleRearLeft: Float = 0f,
    val tireSlipAngleRearRight: Float = 0f,
    val tireCombinedSlipFrontLeft: Float = 0f,
    val tireCombinedSlipFrontRight: Float = 0f,
    val tireCombinedSlipRearLeft: Float = 0f,
    val tireCombinedSlipRearRight: Float = 0f,
    val tireTempFrontLeft: Float = 0f,
    val tireTempFrontRight: Float = 0f,
    val tireTempRearLeft: Float = 0f,
    val tireTempRearRight: Float = 0f,
    val tireWearFrontLeft: Float = 0f,
    val tireWearFrontRight: Float = 0f,
    val tireWearRearLeft: Float = 0f,
    val tireWearRearRight: Float = 0f
) {
    companion object {
        fun fromTelemetryData(data: TelemetryData?): TireModel {
            if(data == null){
                return TireModel()
            }
            return TireModel(
                tireSlipAngleFrontLeft = data.tireSlipAngleFrontLeft,
                tireSlipAngleFrontRight = data.tireSlipAngleFrontRight,
                tireSlipAngleRearLeft = data.tireSlipAngleRearLeft,
                tireSlipAngleRearRight = data.tireSlipAngleRearRight,
                wheelRotationSpeedFrontLeft = data.wheelRotationSpeedFrontLeft,
                wheelRotationSpeedFrontRight = data.wheelRotationSpeedFrontRight,
                wheelRotationSpeedRearRight = data.wheelRotationSpeedRearRight,
                wheelRotationSpeedRearLeft = data.wheelRotationSpeedRearLeft,
                wheelOnRumbleStripFrontLeft = data.wheelOnRumbleStripFrontLeft == 1,
                wheelOnRumbleStripFrontRight = data.wheelOnRumbleStripFrontRight == 1,
                wheelOnRumbleStripRearLeft = data.wheelOnRumbleStripRearLeft == 1,
                wheelOnRumbleStripRearRight = data.wheelOnRumbleStripRearRight == 1,
                wheelInPuddleDepthFrontLeft = data.wheelInPuddleDepthFrontLeft,
                wheelInPuddleDepthFrontRight = data.wheelInPuddleDepthFrontRight,
                wheelInPuddleDepthRearLeft = data.wheelInPuddleDepthRearLeft,
                wheelInPuddleDepthRearRight = data.wheelInPuddleDepthRearRight,
                surfaceRumbleFrontLeft = data.surfaceRumbleFrontLeft,
                surfaceRumbleFrontRight = data.surfaceRumbleFrontRight,
                surfaceRumbleRearLeft = data.surfaceRumbleRearLeft,
                surfaceRumbleRearRight = data.surfaceRumbleRearRight,
                tireSlipRatioFrontLeft = data.tireSlipRatioFrontLeft,
                tireSlipRatioFrontRight = data.tireSlipRatioFrontRight,
                tireSlipRatioRearLeft = data.tireSlipRatioRearLeft,
                tireSlipRatioRearRight = data.tireSlipRatioRearRight,
                tireCombinedSlipFrontLeft = data.tireCombinedSlipFrontLeft,
                tireCombinedSlipFrontRight = data.tireCombinedSlipFrontRight,
                tireCombinedSlipRearLeft = data.tireCombinedSlipRearLeft,
                tireCombinedSlipRearRight = data.tireCombinedSlipRearRight,
                tireTempFrontLeft = data.tireTempFrontLeft,
                tireTempFrontRight = data.tireTempFrontRight,
                tireTempRearLeft = data.tireTempRearLeft,
                tireTempRearRight = data.tireTempRearRight,
                tireWearFrontLeft = data.tireWearFrontLeft,
                tireWearFrontRight = data.tireWearFrontRight,
                tireWearRearLeft = data.tireWearRearLeft,
                tireWearRearRight = data.tireWearRearRight
            )
        }
    }
}