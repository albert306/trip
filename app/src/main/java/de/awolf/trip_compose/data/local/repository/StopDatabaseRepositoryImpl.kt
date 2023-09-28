package de.awolf.trip_compose.data.local.repository

import de.awolf.trip_compose.data.local.data_source.StopDao
import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.repository.StopDatabaseRepository

class StopDatabaseRepositoryImpl(
    private val dao: StopDao
): StopDatabaseRepository {
    override suspend fun insertStop(stop: Stop) {
        dao.insertStop(stop)
    }

    override suspend fun deleteStop(stop: Stop) {
        dao.deleteStop(stop)
    }

    override suspend fun isStopFavourite(id: String): Boolean {
        val stop = dao.getStopById(id) ?: return false
        return stop.isFavourite
    }

    override suspend fun getFavouriteStops(): List<Stop> {
        return dao.getFavouriteStops()
    }
}