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

    override suspend fun toggleFavouriteStopStatus(stop: Stop): Boolean {
        val isCurrentlyFavourite = isStopFavourite(stop.id)
        dao.insertStop(Stop(id = stop.id, name = stop.name, region = stop.region, isFavourite = !isCurrentlyFavourite))
        return isStopFavourite(stop.id)
    }

    override suspend fun isStopFavourite(id: String): Boolean {
        val stopInDb = dao.getStopById(id) ?: return false
        return stopInDb.isFavourite
    }

    override suspend fun getFavouriteStops(): List<Stop> {
        return dao.getFavouriteStops()
    }
}