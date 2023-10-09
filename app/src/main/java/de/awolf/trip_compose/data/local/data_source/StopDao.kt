package de.awolf.trip_compose.data.local.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.awolf.trip_compose.domain.models.Stop

@Dao
interface StopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStop(stop: Stop)

    @Delete
    suspend fun deleteStop(stop: Stop)

    @Query("SELECT * FROM stop WHERE id = :id")
    suspend fun getStopById(id: String): Stop?

    @Query("SELECT * FROM stop WHERE isFavourite = 1 ORDER BY rankingFavourite ASC")
    suspend fun getFavouriteStops(): List<Stop>
}