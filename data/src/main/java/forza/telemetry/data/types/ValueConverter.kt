package forza.telemetry.data.types

fun Float.toMPH(): Float {
    return this * 2.23694f
}
fun Float.toKPH(): Float {
    return this * 3.6f
}
fun Float.toHorsepower(): Float {
    return this * 0.00134102f
}
fun Float.toCelsius(): Float {
    return (((this - 32) * 5) / 9)
}