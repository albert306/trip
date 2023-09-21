package de.awolf.trip_compose.data.local.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import de.awolf.trip_compose.domain.models.Stop

@Suppress("UNUSED")
@Database(
    entities = [Stop::class],
    version = 1
)
abstract class StopDatabase: RoomDatabase() {
    abstract val stopDao: StopDao
}