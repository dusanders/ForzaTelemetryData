package forza.telemetry.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


class FM8DatabaseService(val context: Context) {
    companion object {
        private const val TAG = "FM8DatabaseService"
        private const val FM8_DATABASE_NAME = "FM8_GameData.sqlite3"
        private const val CARS_TABLE = "cars"
        private val CARS_COLUMNS = arrayOf("id", "name", "type")
        private const val TRACKS_TABLE = "tracks"
        private val TRACKS_COLUMNS = arrayOf("id", "circuit", "location", "iocCode", "track", "lengthKm")
    }

    init {
        val path = getNormalizedPath(FM8_DATABASE_NAME)
        Log.d(TAG, "DB path: $path")
        if(!checkDbExists(path)) {
            copyDbFile(path)
        }
        val database = openDatabase()
        database.close()
    }

    private fun getNormalizedPath(filename: String): String {
        return context.getDatabasePath(filename).path;
    }

    private fun checkDbExists(path: String): Boolean {
        val file = File(path);
        return file.exists()
    }

    private fun copyDbFile(dbPath: String) {
        try {
            val dbDir: File? = File(dbPath).getParentFile()
            if (dbDir != null && !dbDir.exists()) {
                dbDir.mkdirs()
            }

            val inputStream: InputStream = context.assets.open(FM8_DATABASE_NAME)
            val outputStream: OutputStream = FileOutputStream(dbPath)

            val buffer = ByteArray(1024)
            var length: Int
            while ((inputStream.read(buffer).also { length = it }) > 0) {
                outputStream.write(buffer, 0, length)
            }

            outputStream.flush()
            outputStream.close()
            inputStream.close()
            Log.d(TAG, "Database copied successfully.")
        } catch (e: Exception) {
            Log.e(TAG, "Error copying database", e)
        }
    }

    private fun debugPrintCars(db: SQLiteDatabase) {
        val table = CARS_TABLE
        val columns = CARS_COLUMNS
        val where = null
        val whereArgs = null
        val groupBy = null
        val having = null
        val orderBy = null
        val carCursor = db.query(
            table,
            columns,
            where,
            whereArgs,
            groupBy,
            having,
            orderBy
        );
        carCursor.moveToFirst()
        do {
            val carName = carCursor.getString(carCursor.getColumnIndexOrThrow(CARS_COLUMNS[1]))
            Log.d(TAG, "Found DB car: $carName")
        }while(carCursor.moveToNext())
        carCursor.close()
    }

    private fun debugPrintTracks(db: SQLiteDatabase) {
        val table = TRACKS_TABLE
        val columns = TRACKS_COLUMNS
        val where = null
        val whereArgs = null
        val groupBy = null
        val having = null
        val orderBy = null
        val trackCursor = db.query(
            table,
            columns,
            where,
            whereArgs,
            groupBy,
            having,
            orderBy
        );
        trackCursor.moveToFirst()
        do {
            val trackName = trackCursor.getString(trackCursor.getColumnIndexOrThrow(TRACKS_COLUMNS[1]))
            val circuit = trackCursor.getString(trackCursor.getColumnIndexOrThrow(TRACKS_COLUMNS[4]))
            Log.d(TAG, "Found DB track: $trackName $circuit")
        }while(trackCursor.moveToNext())
        trackCursor.close()
    }

    private fun printDebugInfo(db: SQLiteDatabase) {
        debugPrintTracks(db)
        debugPrintCars(db)
    }

    private fun openDatabase(): SQLiteDatabase {
        val sqliteDb = SQLiteDatabase.openDatabase(
            getNormalizedPath(FM8_DATABASE_NAME),
            null,
            SQLiteDatabase.OPEN_READONLY
        )
        return sqliteDb;
    }
    fun getFM8TrackInfo(trackId: Int): FM8TrackInfo? {
        var result: FM8TrackInfo? = null
        val database = openDatabase()
        val query = database.query(
            TRACKS_TABLE,
            TRACKS_COLUMNS,
            "id = ?",
            arrayOf("$trackId"),
            null,
            null,
            null
        )
        if(query.moveToFirst()) {
            result = FM8TrackInfo(
                trackId,
                query.getString(query.getColumnIndexOrThrow(TRACKS_COLUMNS[1])),
                query.getString(query.getColumnIndexOrThrow(TRACKS_COLUMNS[2])),
                query.getString(query.getColumnIndexOrThrow(TRACKS_COLUMNS[3])),
                query.getString(query.getColumnIndexOrThrow(TRACKS_COLUMNS[4])),
                query.getFloat(query.getColumnIndexOrThrow(TRACKS_COLUMNS[5]))
            )
        }
        query.close()
        return result
    }
    fun getFM8CarInfo(carId: Int): FM8CarInfo? {
        var result: FM8CarInfo? = null;
        val database = openDatabase()
        val query = database.query(
            CARS_TABLE,
            CARS_COLUMNS,
            "id = ?",
            arrayOf("$carId"),
            null,
            null,
            null
        )
        if(query.moveToFirst()) {
            result = FM8CarInfo(
                carId,
                query.getString(query.getColumnIndexOrThrow(CARS_COLUMNS[1])),
                query.getString(query.getColumnIndexOrThrow(CARS_COLUMNS[2]))
            )
        }
        query.close()
        return result;
    }
}