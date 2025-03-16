package forza.telemetry.data.models

import forza.telemetry.data.TelemetryData

data class SpatialModel(
  val accelerationX: Float = 0f,
  val accelerationY: Float = 0f,
  val accelerationZ: Float = 0f,
  val velocityX: Float = 0f,
  val velocityY: Float = 0f,
  val velocityZ: Float = 0f,
  val angularVelocityX: Float = 0f,
  val angularVelocityY: Float = 0f,
  val angularVelocityZ: Float = 0f,
  val yaw: Float = 0f,
  val pitch: Float = 0f,
  val roll: Float = 0f,
  val positionX: Float = 0f,
  val positionY: Float = 0f,
  val positionZ: Float = 0f
) {

  companion object {
    fun fromTelemetryData(data: TelemetryData?): SpatialModel? {
      if (data == null) {
        return SpatialModel()
      }
      return SpatialModel(
        accelerationX = data.accelerationY,
        accelerationY = data.accelerationY,
        accelerationZ = data.accelerationZ,
        velocityX =  data.velocityX,
        velocityY = data.velocityY,
        velocityZ = data.velocityZ,
        angularVelocityX = data.angularVelocityX,
        angularVelocityY = data.angularVelocityY,
        angularVelocityZ = data.angularVelocityZ,
        yaw = data.yaw,
        pitch = data.pitch,
        roll = data.roll,
        positionX = data.positionX,
        positionY = data.positionY,
        positionZ = data.positionZ
      )
    }
  }
}