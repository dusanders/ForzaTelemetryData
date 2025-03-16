package forza.telemetry.data

import android.content.Context
import android.util.Log
import forza.telemetry.data.database.FM8DatabaseService
import forza.telemetry.data.types.ForzaUdpSocketEvents
import java.net.DatagramPacket
import java.net.DatagramSocket

class ForzaUdpSocket(
  private val context: Context,
  private val eventHandler: ForzaUdpSocketEvents
) {
  private var thread: Thread? = null
  private var requestedPort: Int = 0

  var boundPort: Int? = null
  var isBound: Boolean = boundPort != null

  fun bind(port: Int = 0) {
    if (thread != null) {
      Log.w("ForzaUdpSocket", "Thread already running - stopping")
      thread?.interrupt()
    }
    requestedPort = port;
    Log.d("ForzaUdpSocket", "Starting UDP socket")
    thread = getThread()
    thread?.start()
  }

  fun stop() {
    thread?.interrupt()
    thread = null
    boundPort = null
  }

  private fun getThread(): Thread {
    return object : Thread() {
      private val tag = "UdpSocket.Thread"
      private var socket: DatagramSocket? = null

      override fun run() {
        super.run()
        try {
          socket = DatagramSocket(requestedPort ?: 0)
          boundPort = socket!!.localPort
          eventHandler.onOpen(boundPort!!)
          // Allocate the largest expected bytes - FM8
          val receive = ByteArray(TelemetryParser.FM8_PACKET_LEN)
          val datagramPacket = DatagramPacket(receive, receive.size)
          val fM8DatabaseService = FM8DatabaseService(context)
          while (!interrupted()) {
            // Read UDP socket
            try {
//              Log.d(tag, "Waiting for packet on $boundPort")
              socket?.receive(datagramPacket)
              val data = TelemetryParser.Parse(
                datagramPacket.length,
                datagramPacket.data,
                fM8DatabaseService
              )
//              Log.d(tag, "Received ${datagramPacket.length} bytes")
              eventHandler.onData(data)
            } catch (e: Exception) {
              e.printStackTrace()
            }
          }
        } catch (e: Exception) {
          eventHandler.onSocketError(e)
          boundPort = null
        } finally {
          socket?.close()
          socket = null
        }
      }

      override fun interrupt() {
        super.interrupt()
        socket?.close()
        socket = null
        boundPort = null
      }
    }
  }
}