package forza.telemetry.forzatelemetrydata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import forza.telemetry.data.ForzaInterface
import forza.telemetry.data.ForzaTelemetryBuilder
import forza.telemetry.data.TelemetryData
import java.net.DatagramPacket

class MainActivity :AppCompatActivity(), ForzaInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ForzaTelemetryBuilder(5200, applicationContext)
    }

    override fun onDataReceived(api: TelemetryData?) {
        TODO("Not yet implemented")
    }

    override fun onConnected(api: TelemetryData?, packet: DatagramPacket?) {
        TODO("Not yet implemented")
    }

    override fun onGamePaused() {
        TODO("Not yet implemented")
    }

    override fun onGameUnpaused() {
        TODO("Not yet implemented")
    }
}