package forza.telemetry.data.models

import forza.telemetry.data.TelemetryData
import forza.telemetry.data.types.toHorsepower

data class EngineModel(val data: TelemetryData) {
    val currentRpm = data.currentEngineRpm
    val maxRpm = data.engineMaxRpm
    val idleRpm = data.engineIdleRpm
    val power = data.power
    val torque = data.torque
    val boost = data.boost
    val numOfCylinders = data.numOfCylinders
    val gear = data.gear
    val throttle = data.throttle

    fun getHorsepower(): Float {
        return power.toHorsepower()
    }

    fun getRoundedRpm(): Int {
        return Math.round(currentRpm)
    }
}
