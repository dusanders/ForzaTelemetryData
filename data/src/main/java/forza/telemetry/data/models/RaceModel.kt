package forza.telemetry.data.models

import forza.telemetry.data.TelemetryData

data class RaceModel(
    val isRaceOn: Boolean = false,
    val distanceTraveled: Float = 0f,
    val bestLap: Float = 0f,
    val lastLap: Float = 0f,
    val currentLap: Float = 0f,
    val currentRaceTime: Float = 0f,
    val position: Byte = 0,
    val lapNumber: Short = 0,
    val timestampMS: Long = 0
) {
    companion object {
        fun fromTelemetryData(data: TelemetryData?): RaceModel {
            if(data == null) {
                return RaceModel()
            }
            return RaceModel(
                isRaceOn = data.isRaceOn,
                distanceTraveled = data.distanceTraveled,
                bestLap = data.bestLap,
                lastLap = data.lastLap,
                currentLap = data.currentLap,
                currentRaceTime = data.currentRaceTime,
                position = data.racePosition,
                lapNumber = data.lapNumber,
                timestampMS = data.timeStampMS
            )
        }
    }
}