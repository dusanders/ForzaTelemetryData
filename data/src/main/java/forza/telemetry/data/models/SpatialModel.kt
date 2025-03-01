package forza.telemetry.data.models

import forza.telemetry.data.TelemetryData

class SpatialModel(val data: TelemetryData) {
    val accelerationX = data.accelerationX
    val accelerationY = data.accelerationY
    val accelerationZ = data.accelerationZ
    val velocityX = data.velocityX
    val velocityY = data.velocityY
    val velocityZ = data.velocityZ
    val angularVelocityX = data.angularVelocityX
    val angularVelocityY = data.angularVelocityY
    val angularVelocityZ = data.angularVelocityZ
    val yaw = data.yaw
    val pitch = data.pitch
    val roll = data.roll
    val positionX = data.positionX
    val positionY = data.positionY
    val positionZ = data.positionZ
}