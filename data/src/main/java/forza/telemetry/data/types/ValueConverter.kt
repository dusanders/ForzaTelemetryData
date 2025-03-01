package forza.telemetry.data.types

class ValueConverter {
    fun speedToMPH(speed: Float): Float {
        return speed * 2.23694f
    }
    fun speedToKPH(speed: Float): Float {
        return speed * 3.6f
    }
    fun powerToHorsepower(power: Float): Float {
        return power * 0.00134102f
    }
    fun tempToCelsius(temp: Float): Float {
        return (((temp - 32) * 5) / 9)
    }
}