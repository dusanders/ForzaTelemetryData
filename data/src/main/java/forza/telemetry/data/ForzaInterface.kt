package forza.telemetry.data

import android.util.Log
import forza.telemetry.data.database.DatabaseService
import java.net.BindException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketException
import java.net.UnknownHostException

interface ForzaInterface {
  fun startConnection(port: Int, databaseService: DatabaseService): Thread {
    return object : Thread() {
      private val tag = "ForzaInterface.Thread"
      private var datagramSocket: DatagramSocket? = null

      @Synchronized
      override fun run() {
        super.run()
        try {
          datagramSocket = DatagramSocket(port)
          // Allocate the largest expected bytes - FM8
          val receive = ByteArray(TelemetryParser.FM8_PACKET_LEN)
          val datagramPacket = DatagramPacket(receive, receive.size)
          var isPaused = false
          var isConnected = false
          while (!interrupted()) {
            // Read UDP socket
            try {
              datagramSocket?.receive(datagramPacket)
              if (!isConnected) {
                val tempApi = TelemetryParser.Parse(
                  datagramPacket.length,
                  datagramPacket.data,
                  databaseService
                )
                if (tempApi.timeStampMS != 0L) {
                  //Set ForzaApi to null if game is paused, as all values will return 0
                  if (!tempApi.isRaceOn) {
                    onConnected(null, datagramPacket)
                  } else {
                    onConnected(tempApi, datagramPacket)
                  }
                  isConnected = true
                }
              }
            } catch (e: Exception) {
              e.printStackTrace()
            }
            //Send data to the ForzaApi parsing class
            try {
              val data = datagramPacket.data
              val api: TelemetryData =
                TelemetryParser.Parse(
                  datagramPacket.length,
                  data,
                  databaseService
                )
              //Call onGamePaused when isRaceOn is false, call onGameUnpaused when true while game is paused
              if (!api.isRaceOn && !isPaused) {
                onGamePaused()
                isPaused = true
              } else if (api.isRaceOn && isPaused) {
                onGameUnpaused()
                isPaused = false
              }
              //Send datastream every single loop unless game is paused
              if (!isPaused) onDataReceived(api)
            } catch (e: Exception) {
              e.printStackTrace()
            }
          }
        } catch (e: SocketException) {
          onSocketException(e)
        } finally {
          datagramSocket?.close()
        }
      }

      override fun interrupt() {
        super.interrupt()
        Log.w(tag, "interrupt() - Closing DatagramSocket")
        datagramSocket?.close()
      }
    }
  }
  fun onSocketException(e: SocketException)
  fun onDataReceived(api: TelemetryData?)
  fun onConnected(api: TelemetryData?, packet: DatagramPacket?)
  fun onGamePaused()
  fun onGameUnpaused()
}
