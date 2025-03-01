package forza.telemetry.data.models

import forza.telemetry.data.TelemetryData

class RaceModel(val data: TelemetryData) {
    val isRaceOn = data.isRaceOn
    val distanceTraveled = data.distanceTraveled
    val bestLap = data.bestLap
    val lastLap = data.lastLap
    val currentLap = data.currentLap
    val currentRaceTime = data.currentRaceTime
    val position = data.racePosition
    val lapNumber = data.lapNumber
    val timestampMS = data.timeStampMS
}