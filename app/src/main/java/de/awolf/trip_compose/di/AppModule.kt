package de.awolf.trip_compose.di

import android.content.Context
import de.awolf.trip_compose.domain.repository.StopDatabaseRepository
import de.awolf.trip_compose.domain.repository.VvoServiceRepository
import de.awolf.trip_compose.domain.use_case.GetRecommendedStopsUseCase
import de.awolf.trip_compose.domain.use_case.GetStopMonitorUseCase
import de.awolf.trip_compose.domain.use_case.UseCases

interface AppModule {
    val vvoServiceRepository: VvoServiceRepository
    val stopDatabaseRepository: StopDatabaseRepository
    val useCases: UseCases
}

class AppModuleImpl(
    private val appContext: Context
): AppModule {

    override val vvoServiceRepository: VvoServiceRepository by lazy {
        VvoServiceRepository.create()
    }

    override val stopDatabaseRepository: StopDatabaseRepository by lazy {
        StopDatabaseRepository.create(appContext)
    }

    override val useCases: UseCases by lazy {
        UseCases(
            GetRecommendedStopsUseCase(vvoServiceRepository, stopDatabaseRepository),
            GetStopMonitorUseCase(vvoServiceRepository),
        )
    }
}