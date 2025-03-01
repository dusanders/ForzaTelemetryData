package forza.telemetry.data.database

data class FM8TrackInfo (
    val id: Int,
    val circuit: String,
    val location: String,
    val iocCode: String,
    val track: String,
    val lengthKm: Float
)