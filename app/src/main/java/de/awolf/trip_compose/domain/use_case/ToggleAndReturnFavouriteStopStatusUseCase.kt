package de.awolf.trip_compose.domain.use_case

import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.repository.StopDatabaseRepository

class ToggleAndReturnFavouriteStopStatusUseCase(
    private val stopDatabaseRepository: StopDatabaseRepository
) {

    suspend operator fun invoke(stop: Stop): Boolean {
        return stopDatabaseRepository.toggleFavouriteStopStatus(stop)
    }
}