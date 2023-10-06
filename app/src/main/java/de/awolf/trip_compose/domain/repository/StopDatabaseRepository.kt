package de.awolf.trip_compose.domain.repository

import android.content.Context
import androidx.room.Room
import de.awolf.trip_compose.data.local.data_source.StopDatabase
import de.awolf.trip_compose.data.local.repository.StopDatabaseRepositoryImpl
import de.awolf.trip_compose.domain.models.Stop

interface StopDatabaseRepository {
    suspend fun insertStop(stop: Stop)

    suspend fun deleteStop(stop: Stop)

    suspend fun toggleFavouriteStopStatus(stop: Stop): Boolean

    suspend fun isStopFavourite(id: String): Boolean

    suspend fun getFavouriteStops(): List<Stop>

    companion object {
        fun create(appContext: Context): StopDatabaseRepositoryImpl {
            return StopDatabaseRepositoryImpl(
                Room.databaseBuilder(
                    appContext,
                    StopDatabase::class.java,
                    StopDatabase.DATABASE_NAME
                ).build().dao
            )
        }
    }
}