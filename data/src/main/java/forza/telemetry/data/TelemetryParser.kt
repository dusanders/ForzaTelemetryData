package forza.telemetry.data

import forza.telemetry.data.database.FM8DatabaseService
import java.math.BigDecimal
import java.math.RoundingMode
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.reflect.KClass

class TelemetryParser private constructor(
    val buffLen: Int,
    bytes: ByteArray,
    private val fM8DatabaseService: FM8DatabaseService
) {
    companion object {
        const val DASH_PACKET_LEN: Int = 311
        const val FH4_PACKET_LEN: Int = 324
        const val FM8_PACKET_LEN: Int = 331
        fun Parse(
            buffLen: Int,
            bytes: ByteArray,
            fM8DatabaseService: FM8DatabaseService
        ): TelemetryData {
            val instance = TelemetryParser(buffLen, bytes, fM8DatabaseService)
            return instance.doParseAsTelemetryData()
        }
    }

    private var buffer: ByteBuffer = ByteBuffer.wrap(bytes)

    init {
        buffer.order(ByteOrder.LITTLE_ENDIAN)
    }

    fun doParseAsTelemetryData(): TelemetryData {
        val gameVersion = parseGameVersion()
        val isRaceOn = parseBytesOrDefault(Int::class) == 1
        val timeStampMS = parseBytesOrDefault(Long::class)
        val engineMaxRpm = parseBytesOrDefault(Float::class)
        val engineIdleRpm = parseBytesOrDefault(Float::class)
        val currentEngineRpm = parseBytesOrDefault(Float::class)
        val accelerationX = parseBytesOrDefault(Float::class) * 100
        val accelerationY = parseBytesOrDefault(Float::class) * 100
        val accelerationZ = parseBytesOrDefault(Float::class) * 100
        val velocityX = parseBytesOrDefault(Float::class) * 100
        val velocityY = parseBytesOrDefault(Float::class) * 100
        val velocityZ = parseBytesOrDefault(Float::class) * 100
        val angularVelocityX = parseBytesOrDefault(Float::class) * 100
        val angularVelocityY = parseBytesOrDefault(Float::class) * 100
        val angularVelocityZ = parseBytesOrDefault(Float::class) * 100
        val yaw = parseBytesOrDefault(Float::class) * 100
        val pitch = parseBytesOrDefault(Float::class) * 100
        val roll = parseBytesOrDefault(Float::class) * 100
        val normalizedSuspensionTravelFrontLeft = parseBytesOrDefault(Float::class) * 100
        val normalizedSuspensionTravelFrontRight = parseBytesOrDefault(Float::class) * 100
        val normalizedSuspensionTravelRearLeft = parseBytesOrDefault(Float::class) * 100
        val normalizedSuspensionTravelRearRight = parseBytesOrDefault(Float::class) * 100
        val tireSlipRatioFrontLeft = parseBytesOrDefault(Float::class) * 100
        val tireSlipRatioFrontRight = parseBytesOrDefault(Float::class) * 100
        val tireSlipRatioRearLeft = parseBytesOrDefault(Float::class) * 100
        val tireSlipRatioRearRight = parseBytesOrDefault(Float::class) * 100
        val wheelRotationSpeedFrontLeft = parseBytesOrDefault(Float::class) * 100
        val wheelRotationSpeedFrontRight = parseBytesOrDefault(Float::class) * 100
        val wheelRotationSpeedRearLeft = parseBytesOrDefault(Float::class) * 100
        val wheelRotationSpeedRearRight = parseBytesOrDefault(Float::class) * 100
        val wheelOnRumbleStripFrontLeft = parseBytesOrDefault(Int::class)
        val wheelOnRumbleStripFrontRight = parseBytesOrDefault(Int::class)
        val wheelOnRumbleStripRearLeft = parseBytesOrDefault(Int::class)
        val wheelOnRumbleStripRearRight = parseBytesOrDefault(Int::class)
        val wheelInPuddleDepthFrontLeft = parseBytesOrDefault(Float::class)
        val wheelInPuddleDepthFrontRight = parseBytesOrDefault(Float::class)
        val wheelInPuddleDepthRearLeft = parseBytesOrDefault(Float::class)
        val wheelInPuddleDepthRearRight = parseBytesOrDefault(Float::class)
        val surfaceRumbleFrontLeft = parseBytesOrDefault(Float::class)
        val surfaceRumbleFrontRight = parseBytesOrDefault(Float::class)
        val surfaceRumbleRearLeft = parseBytesOrDefault(Float::class)
        val surfaceRumbleRearRight = parseBytesOrDefault(Float::class)
        val tireSlipAngleFrontLeft = toAngle(parseBytesOrDefault(Float::class))
        val tireSlipAngleFrontRight = toAngle(parseBytesOrDefault(Float::class))
        val tireSlipAngleRearLeft = toAngle(parseBytesOrDefault(Float::class))
        val tireSlipAngleRearRight = toAngle(parseBytesOrDefault(Float::class))
        val tireCombinedSlipFrontLeft = parseBytesOrDefault(Float::class) * 100
        val tireCombinedSlipFrontRight = parseBytesOrDefault(Float::class) * 100
        val tireCombinedSlipRearLeft = parseBytesOrDefault(Float::class) * 100
        val tireCombinedSlipRearRight = parseBytesOrDefault(Float::class) * 100
        val suspensionTravelMetersFrontLeft = parseBytesOrDefault(Float::class) * 100
        val suspensionTravelMetersFrontRight = parseBytesOrDefault(Float::class) * 100
        val suspensionTravelMetersRearLeft = parseBytesOrDefault(Float::class) * 100
        val suspensionTravelMetersRearRight = parseBytesOrDefault(Float::class) * 100
        val carId = parseBytesOrDefault(Int::class)
        val carClass = parseBytesOrDefault(Int::class)
        val carPerformanceIndex = parseBytesOrDefault(Int::class)
        val drivetrainType = parseBytesOrDefault(Int::class)
        val numOfCylinders = parseBytesOrDefault(Int::class)
        var carType = 0
        var objectHit = 0L
        if (gameVersion == ForzaConstants.GameVersion.HORIZON) {
            carType = parseBytesOrDefault(Int::class)
            objectHit = parseBytesOrDefault(Long::class)
        }
        val positionX = parseBytesOrDefault(Float::class)
        val positionY = parseBytesOrDefault(Float::class)
        val positionZ = parseBytesOrDefault(Float::class)
        val speed = parseBytesOrDefault(Float::class)
        val power = parseBytesOrDefault(Float::class)
        val torque = parseBytesOrDefault(Float::class)
        val tireTempFrontLeft = parseBytesOrDefault(Float::class)
        val tireTempFrontRight = parseBytesOrDefault(Float::class)
        val tireTempRearLeft = parseBytesOrDefault(Float::class)
        val tireTempRearRight = parseBytesOrDefault(Float::class)
        val boost = parseBytesOrDefault(Float::class)
        val fuel = BigDecimal((parseBytesOrDefault(Float::class) * 100).toDouble()).setScale(
            2,
            RoundingMode.DOWN
        ).toFloat()
        val distanceTraveled = parseBytesOrDefault(Float::class)
        val bestLap = parseBytesOrDefault(Float::class)
        val lastLap = parseBytesOrDefault(Float::class)
        val currentLap = parseBytesOrDefault(Float::class)
        val currentRaceTime = parseBytesOrDefault(Float::class)
        val lapNumber = parseBytesOrDefault(Short::class)
        val racePosition = parseBytesOrDefault(Byte::class)
        val throttle = (parseBytesOrDefault(Byte::class).toInt() and 0xff) * 100 / 255
        val brake = (parseBytesOrDefault(Byte::class).toInt() and 0xff) * 100 / 255
        val clutch = (parseBytesOrDefault(Byte::class).toInt() and 0xff) * 100 / 255
        val handbrake = (parseBytesOrDefault(Byte::class).toInt() and 0xff) * 100 / 255
        val gear = parseBytesOrDefault(Byte::class).toInt() and 0xff
        val steer = (parseBytesOrDefault(Byte::class).toInt() and 0xff) * 100 / 127
        val normalizedDrivingLine = (parseBytesOrDefault(Byte::class).toInt() and 0xff) * 100 / 127
        val normalizedAIBrakeDifference =
            (parseBytesOrDefault(Byte::class).toInt() and 0xff) * 100 / 127
        var tireWearFrontLeft = 0f
        var tireWearFrontRight = 0f
        var tireWearRearLeft = 0f
        var tireWearRearRight = 0f
        var trackID = 0
        if (gameVersion != ForzaConstants.GameVersion.HORIZON) {
            tireWearFrontLeft = parseBytesOrDefault(Float::class) * 100
            tireWearFrontRight = parseBytesOrDefault(Float::class) * 100
            tireWearRearLeft = parseBytesOrDefault(Float::class) * 100
            tireWearRearRight = parseBytesOrDefault(Float::class) * 100
            trackID = parseBytesOrDefault(Int::class)
        }
        return TelemetryData(
            fM8DatabaseService,
            buffer.array(),
            parseGameVersion(),
            isRaceOn,
            timeStampMS,
            engineMaxRpm,
            engineIdleRpm,
            currentEngineRpm,
            accelerationX,
            accelerationY,
            accelerationZ,
            velocityX,
            velocityY,
            velocityZ,
            angularVelocityX,
            angularVelocityY,
            angularVelocityZ,
            yaw,
            pitch,
            roll,
            normalizedSuspensionTravelFrontLeft,
            normalizedSuspensionTravelFrontRight,
            normalizedSuspensionTravelRearLeft,
            normalizedSuspensionTravelRearRight,
            tireSlipRatioFrontLeft,
            tireSlipRatioFrontRight,
            tireSlipRatioRearLeft,
            tireSlipRatioRearRight,
            wheelRotationSpeedFrontLeft,
            wheelRotationSpeedFrontRight,
            wheelRotationSpeedRearLeft,
            wheelRotationSpeedRearRight,
            wheelOnRumbleStripFrontLeft,
            wheelOnRumbleStripFrontRight,
            wheelOnRumbleStripRearLeft,
            wheelOnRumbleStripRearRight,
            wheelInPuddleDepthFrontLeft,
            wheelInPuddleDepthFrontRight,
            wheelInPuddleDepthRearLeft,
            wheelInPuddleDepthRearRight,
            surfaceRumbleFrontLeft,
            surfaceRumbleFrontRight,
            surfaceRumbleRearLeft,
            surfaceRumbleRearRight,
            tireSlipAngleFrontLeft,
            tireSlipAngleFrontRight,
            tireSlipAngleRearLeft,
            tireSlipAngleRearRight,
            tireCombinedSlipFrontLeft,
            tireCombinedSlipFrontRight,
            tireCombinedSlipRearLeft,
            tireCombinedSlipRearRight,
            suspensionTravelMetersFrontLeft,
            suspensionTravelMetersFrontRight,
            suspensionTravelMetersRearLeft,
            suspensionTravelMetersRearRight,
            carClass,
            carPerformanceIndex,
            drivetrainType,
            numOfCylinders,
            carType,
            objectHit,
            carId,
            positionX,
            positionY,
            positionZ,
            speed,
            power,
            torque,
            tireTempFrontLeft,
            tireTempFrontRight,
            tireTempRearLeft,
            tireTempRearRight,
            boost,
            fuel,
            distanceTraveled,
            bestLap,
            lastLap,
            currentLap,
            currentRaceTime,
            lapNumber,
            racePosition,
            throttle,
            brake,
            clutch,
            handbrake,
            gear,
            steer,
            normalizedDrivingLine,
            normalizedAIBrakeDifference,
            tireWearFrontLeft,
            tireWearFrontRight,
            tireWearRearLeft,
            tireWearRearRight,
            trackID
        )
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Number> parseBytesOrDefault(type: KClass<T>): T {
        if (!buffer.hasRemaining()) {
            return 0 as T;
        }
        when (type) {
            Int::class ->
                if (buffer.remaining() >= 4) {
                    return buffer.getInt() as T
                }

            Long::class ->
                if (buffer.remaining() >= 4) {
                    return Integer.toUnsignedLong(buffer.getInt()) as T
                }

            Short::class ->
                if (buffer.remaining() >= 2) {
                    return buffer.getShort() as T
                }

            Float::class ->
                if (buffer.remaining() >= 4) {
                    return buffer.getFloat() as T
                }

            Byte::class ->
                if (buffer.remaining() >= 1) {
                    return buffer.get() as T
                }
        }
        return 0 as T;
    }

    private fun toAngle(value: Float): Float {
        return Math.round(value * 180 / Math.PI).toFloat()
    }

    private fun parseGameVersion(): ForzaConstants.GameVersion {
        return if (buffLen == FH4_PACKET_LEN) ForzaConstants.GameVersion.HORIZON
        else if (buffLen == DASH_PACKET_LEN) ForzaConstants.GameVersion.MOTORSPORT_7
        else ForzaConstants.GameVersion.MOTORSPORT_8
    }
}