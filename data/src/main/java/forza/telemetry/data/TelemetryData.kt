package forza.telemetry.data

import forza.telemetry.data.database.DatabaseService

data class TelemetryData(
    val gameVersion: ForzaConstants.GameVersion,
    val isRaceOn: Boolean,
    val timeStampMS: Long,
    val engineMaxRpm: Float,
    val engineIdleRpm: Float,
    val currentEngineRpm: Float,
    val accelerationX: Float,
    val accelerationY: Float,
    val accelerationZ: Float,
    val velocityX: Float,
    val velocityY: Float,
    val velocityZ: Float,
    val angularVelocityX: Float,
    val angularVelocityY: Float,
    val angularVelocityZ: Float,
    val yaw: Float,
    val pitch: Float,
    val roll: Float,
    val normalizedSuspensionTravelFrontLeft: Float,
    val normalizedSuspensionTravelFrontRight: Float,
    val normalizedSuspensionTravelRearLeft: Float,
    val normalizedSuspensionTravelRearRight: Float,
    val tireSlipRatioFrontLeft: Float,
    val tireSlipRatioFrontRight: Float,
    val tireSlipRatioRearLeft: Float,
    val tireSlipRatioRearRight: Float,
    val wheelRotationSpeedFrontLeft: Float,
    val wheelRotationSpeedFrontRight: Float,
    val wheelRotationSpeedRearLeft: Float,
    val wheelRotationSpeedRearRight: Float,
    val wheelOnRumbleStripFrontLeft: Int,
    val wheelOnRumbleStripFrontRight: Int,
    val wheelOnRumbleStripRearLeft: Int,
    val wheelOnRumbleStripRearRight: Int,
    val wheelInPuddleDepthFrontLeft: Float,
    val wheelInPuddleDepthFrontRight: Float,
    val wheelInPuddleDepthRearLeft: Float,
    val wheelInPuddleDepthRearRight: Float,
    val surfaceRumbleFrontLeft: Float,
    val surfaceRumbleFrontRight: Float,
    val surfaceRumbleRearLeft: Float,
    val surfaceRumbleRearRight: Float,
    val tireSlipAngleFrontLeft: Long,
    val tireSlipAngleFrontRight: Long,
    val tireSlipAngleRearLeft: Long,
    val tireSlipAngleRearRight: Long,
    val tireCombinedSlipFrontLeft: Float,
    val tireCombinedSlipFrontRight: Float,
    val tireCombinedSlipRearLeft: Float,
    val tireCombinedSlipRearRight: Float,
    val suspensionTravelMetersFrontLeft: Float,
    val suspensionTravelMetersFrontRight: Float,
    val suspensionTravelMetersRearLeft: Float,
    val suspensionTravelMetersRearRight: Float,
    val carClass: Int,
    val carPerformanceIndex: Int,
    val drivetrainType: Int,
    val numOfCylinders: Int,
    val carType: Int,
    val objectHit: Long,
    val carId: Int,
    val positionX: Float,
    val positionY: Float,
    val positionZ: Float,
    val speed: Float,
    val power: Float,
    val torque: Float,
    val tireTempFrontLeft: Float,
    val tireTempFrontRight: Float,
    val tireTempRearLeft: Float,
    val tireTempRearRight: Float,
    val boost: Float,
    val fuel: Float,
    val distanceTraveled: Float,
    val bestLap: Float,
    val lastLap: Float,
    val currentLap: Float,
    val currentRaceTime: Float,
    val lapNumber: Short,
    val racePosition: Byte,
    val throttle: Int,
    val brake: Int,
    val clutch: Int,
    val handbrake: Int,
    val gear: Int,
    val steer: Int,
    val normalizedDrivingLine: Int,
    val normalizedAIBrakeDifference: Int,
    var tireWearFrontLeft: Float = 0F,
    var tireWearFrontRight: Float = 0f,
    var tireWearRearLeft: Float = 0f,
    var tireWearRearRight: Float = 0f,
    var trackID: Int = 0,
    val database: DatabaseService
) {

    fun speedMPH(): Float {
        return speed * 2.23694f
    }

    fun speedKPH(): Float {
        return speed * 3.6f
    }

    fun horsepower(): Float {
        return power * 0.00134102f
    }

    fun celsius(temp: Float): Float {
        return (((temp - 32) * 5) / 9)
    }
}