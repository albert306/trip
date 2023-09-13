package de.awolf.trip_compose.di

import android.content.Context
import de.awolf.trip_compose.domain.repository.VvoService
import de.awolf.trip_compose.domain.use_case.GetRecommendedStopsUseCase
import de.awolf.trip_compose.domain.use_case.GetStopMonitorDeparturesUseCase
import de.awolf.trip_compose.domain.use_case.VvoServiceUseCases

interface AppModule {
    val vvoService: VvoService
    val vvoServiceUseCases: VvoServiceUseCases
}

@Suppress("UNUSED")
class AppModuleImpl(
    private val appContext: Context
): AppModule {

    override val vvoService: VvoService by lazy {
        VvoService.create()
    }

    override val vvoServiceUseCases: VvoServiceUseCases by lazy {
        VvoServiceUseCases(
            GetRecommendedStopsUseCase(vvoService),
            GetStopMonitorDeparturesUseCase(vvoService)
        )
    }
}