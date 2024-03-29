package de.awolf.trip_compose.domain.repository

import de.awolf.trip_compose.domain.models.Mode
import de.awolf.trip_compose.data.remote.repository.VvoServiceRepositoryImpl
import de.awolf.trip_compose.domain.models.StopFinderInfo
import de.awolf.trip_compose.domain.models.StopMonitorInfo
import de.awolf.trip_compose.domain.models.StopScheduleItem
import de.awolf.trip_compose.domain.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.time.LocalDateTime

interface VvoServiceRepository {

    suspend fun monitorStop(
        stopId: String,
        limit: Int = 5,
        time: LocalDateTime = LocalDateTime.now(),
        isArrival: Boolean = false,
        shorttermchanges: Boolean = true,
        modeOfTransport: List<String> = Mode.getAllLocal()
    ): Resource<StopMonitorInfo>

    suspend fun getStopByName(
        query: String,
        limit: Int = 15,
        stopsOnly: Boolean = true,
        regionalOnly: Boolean = true,
        stopShortcuts: Boolean = false
    ): Resource<StopFinderInfo>

    suspend fun getDetailedStopSchedule(
        stopId: String,
        departureId: String,
        time: LocalDateTime
    ): Resource<List<StopScheduleItem>>

    companion object {
        fun create(): VvoServiceRepository {
            return VvoServiceRepositoryImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                            encodeDefaults = true
                        })
                    }
                }
            )
        }
    }

}