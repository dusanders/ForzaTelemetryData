package forza.telemetry.data

import android.content.Context
import android.util.Log
import forza.telemetry.data.database.DatabaseService

class ForzaTelemetryBuilder(private val port: Int, context: Context) {
    val _tag = "ForzaTelemetryBuilder";
    var listener: ForzaInterface? = null
    var connectionThread: Thread? = null
    private var database: DatabaseService

    init {
        database = DatabaseService(context)
        val debug = database.getFM8CarInfo(247)
        Log.d(_tag, "debug car ${debug?.id} ${debug?.name} - ${debug?.type}")
    }

    fun addListener(listener: ForzaInterface): ForzaTelemetryBuilder {
        this.listener = listener
        return this
    }

    val thread: Thread?
        get() {
            if (connectionThread == null) {
                connectionThread = listener?.startConnection(port, database)
            }
            return connectionThread
        }
}
