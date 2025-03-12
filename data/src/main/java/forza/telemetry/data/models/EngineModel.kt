package forza.telemetry.data.models

import forza.telemetry.data.TelemetryData
import forza.telemetry.data.types.toHorsepower

data class EngineModel(
  val currentRpm: Float = 0f,
  val maxRpm: Float = 0f,
  val idleRpm: Float = 0f,
  val power: Float = 0f,
  val torque: Float = 0f,
  val boost: Float = 0f,
  val numOfCylinders: Int = 0,
  val gear: Int = 0,
  val throttle: Int = 0
) {
  companion object {
    fun fromTelemetryData(data: TelemetryData): EngineModel {
      return EngineModel(
        currentRpm = data.currentEngineRpm,
        maxRpm = data.engineMaxRpm,
        idleRpm = data.engineIdleRpm,
        power = data.power,
        torque = data.torque,
        boost = data.boost,
        numOfCylinders = data.numOfCylinders,
        gear = data.gear,
        throttle = data.throttle
      )
    }
  }

    fun getHorsepower(): Float {
        return power.toHorsepower()
    }

    fun getRoundedRpm(): Int {
        return Math.round(currentRpm)
    }
}
