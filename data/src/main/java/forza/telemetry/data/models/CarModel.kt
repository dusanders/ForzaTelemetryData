package forza.telemetry.data.models

import forza.telemetry.data.ForzaConstants
import forza.telemetry.data.TelemetryData
import forza.telemetry.data.database.DatabaseService
import forza.telemetry.data.database.FM8CarInfo

class CarModel(val data: TelemetryData, databaseService: DatabaseService) {
    val id = data.carId
    val performanceIndex = data.carPerformanceIndex
    val speed = data.speed
    val throttle = data.throttle
    val brake = data.brake
    val handbrake = data.handbrake
    val clutch = data.clutch
    val gear = data.gear
    val steer = data.steer
    val fuel = data.fuel
    val objectHit = data.objectHit
    val normalizedDrivingLine = data.normalizedDrivingLine
    val normalizedAIBrakeDifferenct = data.normalizedAIBrakeDifference
    val numOfCylinders = data.numOfCylinders

    private var fM8CarInfo: FM8CarInfo? = null

    init {
        if(data.gameVersion == ForzaConstants.GameVersion.MOTORSPORT_8) {
            fM8CarInfo = databaseService.getFM8CarInfo(data.carId)
        }
    }

    fun getSpeedMPH(): Float {
        return speed * 2.23694f
    }

    fun getSpeedKPH(): Float {
        return speed * 3.6f
    }

    fun getDrivetrain(): ForzaConstants.Drivetrain {
        when (data.drivetrainType) {
            0 -> return ForzaConstants.Drivetrain.FWD
            1 -> return ForzaConstants.Drivetrain.RWD
            2 -> return ForzaConstants.Drivetrain.AWD
        }
        return ForzaConstants.Drivetrain.UNKNOWN
    }

    fun getName(): String {
        return fM8CarInfo?.name ?: "-"
    }

    fun getType(): String {
        if(fM8CarInfo != null) {
            return fM8CarInfo!!.type
        } else if(data.gameVersion == ForzaConstants.GameVersion.MOTORSPORT_7) {
            return "-"
        }
        // Send Horizon values
        var result = "-"
        when (data.carType) {
            11 -> result = "Modern Super Cars"
            12 -> result = "Retro Super Cars"
            13 -> result = "Hyper Cars"
            14 -> result = "Retro Saloons"
            16 -> result = "Vans & Utility"
            17 -> result = "Retro Sports Cars"
            18 -> result = "Modern Sports Cars"
            19 -> result = "Super Saloons"
            20 -> result = "Classic Racers"
            21 -> result = "Cult Cars"
            22 -> result = "Rare Classics"
            25 -> result = "Super Hot Hatch"
            29 -> result = "Rods & Customs"
            30 -> result = "Retro Muscle"
            31 -> result = "Modern Muscle"
            32 -> result = "Retro Rally"
            33 -> result = "Classic Rally"
            34 -> result = "Rally Monsters"
            35 -> result = "Modern Rally"
            36 -> result = "GT Cars"
            37 -> result = "Super GT"
            38 -> result = "Extreme Offroad"
            39 -> result = "Sports Utility Heroes"
            40 -> result = "Offroad"
            41 -> result = "Offroad Buggies"
            42 -> result = "Classic Sports Cars"
            43 -> result = "Track Toys"
            44 -> result = "Vintage Racers"
            45 -> result = "Trucks"
        }
        return result;
    }

    fun getCarClass(): ForzaConstants.CarClass {
        val isHorizon = data.gameVersion == ForzaConstants.GameVersion.HORIZON;
        when (data.carClass) {
            0 -> return if (isHorizon)
                ForzaConstants.CarClass.D
            else
                ForzaConstants.CarClass.E

            1 -> return if (isHorizon)
                ForzaConstants.CarClass.C
            else
                ForzaConstants.CarClass.D

            2 -> return if (isHorizon)
                ForzaConstants.CarClass.B
            else
                ForzaConstants.CarClass.C

            3 -> return if (isHorizon)
                ForzaConstants.CarClass.A
            else
                ForzaConstants.CarClass.B

            4 -> return if (isHorizon)
                ForzaConstants.CarClass.S
            else
                ForzaConstants.CarClass.A

            5 -> return if (isHorizon)
                ForzaConstants.CarClass.S1
            else
                ForzaConstants.CarClass.S

            6 -> return if (isHorizon)
                ForzaConstants.CarClass.S2
            else
                ForzaConstants.CarClass.R

            7 -> return ForzaConstants.CarClass.P
            8 -> return ForzaConstants.CarClass.X
        }
        return ForzaConstants.CarClass.UNKNOWN
    }

}