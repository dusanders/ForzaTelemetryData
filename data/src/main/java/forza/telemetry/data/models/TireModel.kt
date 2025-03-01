package forza.telemetry.data.models

import forza.telemetry.data.TelemetryData

class TireModel(val data: TelemetryData) {
    val tireSlipRatioFrontLeft = data.tireSlipRatioFrontLeft
    val tireSlipRatioFrontRight = data.tireSlipRatioFrontRight
    val tireSlipRatioRearLeft = data.tireSlipRatioRearLeft
    val tireSlipRatioRearRight = data.tireSlipRatioRearRight
    val wheelRotationSpeedFrontLeft = data.wheelRotationSpeedFrontLeft
    val wheelRotationSpeedFrontRight = data.wheelRotationSpeedFrontRight
    val wheelRotationSpeedRearLeft = data.wheelRotationSpeedRearLeft
    val wheelRotationSpeedRearRight = data.wheelRotationSpeedRearRight
    val wheelOnRumbleStripFrontLeft = data.wheelOnRumbleStripFrontLeft
    val wheelOnRumbleStripFrontRight = data.wheelOnRumbleStripFrontRight
    val wheelOnRumbleStripRearLeft = data.wheelOnRumbleStripRearLeft
    val wheelOnRumbleStripRearRight = data.wheelOnRumbleStripRearRight
    val wheelInPuddleDepthFrontLeft = data.wheelInPuddleDepthFrontLeft
    val wheelInPuddleDepthFrontRight = data.wheelInPuddleDepthFrontRight
    val wheelInPuddleDepthRearLeft = data.wheelInPuddleDepthRearLeft
    val wheelInPuddleDepthRearRight = data.wheelInPuddleDepthRearRight
    val surfaceRumbleFrontLeft = data.surfaceRumbleFrontLeft
    val surfaceRumbleFrontRight = data.surfaceRumbleFrontRight
    val surfaceRumbleRearLeft = data.surfaceRumbleRearLeft
    val surfaceRumbleRearRight = data.surfaceRumbleRearRight
    val tireSlipAngleFrontLeft = data.tireSlipAngleFrontLeft
    val tireSlipAngleFrontRight = data.tireSlipAngleFrontRight
    val tireSlipAngleRearLeft = data.tireSlipAngleRearLeft
    val tireSlipAngleRearRight = data.tireSlipAngleRearRight
    val tireCombinedSlipFrontLeft = data.tireCombinedSlipFrontLeft
    val tireCombinedSlipFrontRight = data.tireCombinedSlipFrontRight
    val tireCombinedSlipRearLeft = data.tireCombinedSlipRearLeft
    val tireCombinedSlipRearRight = data.tireCombinedSlipRearRight
    val tireTempFrontLeft = data.tireTempFrontLeft
    val tireTempFrontRight = data.tireTempFrontRight
    val tireTempRearLeft = data.tireTempRearLeft
    val tireTempRearRight = data.tireTempRearRight
    var tireWearFrontLeft = data.tireWearFrontLeft
    var tireWearFrontRight = data.tireWearFrontRight
    var tireWearRearLeft = data.tireWearRearLeft
    var tireWearRearRight = data.tireWearRearRight
}