package de.awolf.trip_compose.data.local.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import de.awolf.trip_compose.domain.models.Stop

@Database(
    entities = [Stop::class],
    version = 1
)
abstract class StopDatabase: RoomDatabase() {
    abstract val dao: StopDao

    companion object {
        const val DATABASE_NAME = "stops_db"
    }
}