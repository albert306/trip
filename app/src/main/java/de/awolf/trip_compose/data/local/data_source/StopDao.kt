package de.awolf.trip_compose.data.local.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.awolf.trip_compose.domain.models.Stop
import kotlinx.coroutines.flow.Flow

@Suppress("UNUSED")
@Dao
interface StopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStop(stop: Stop)

    @Delete
    suspend fun deleteStop(stop: Stop)

    @Query("SELECT * FROM stop WHERE isFavourite = 'true'")
    fun getFavouriteStops(): Flow<List<Stop>>
}