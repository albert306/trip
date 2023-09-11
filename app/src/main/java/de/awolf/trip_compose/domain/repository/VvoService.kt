package de.awolf.trip_compose.domain.repository

import de.awolf.trip_compose.data.remote.dto.stop_finder.StopFinderResponseDto
import de.awolf.trip_compose.domain.models.Mode
import de.awolf.trip_compose.data.remote.dto.stop_monitor.StopMonitorResponseDto
import de.awolf.trip_compose.data.remote.repository.VvoServiceImpl
import de.awolf.trip_compose.domain.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import java.time.LocalDateTime

interface VvoService {

    suspend fun monitorStop(
        stopId: String,
        limit: Int = 5,
        time: LocalDateTime = LocalDateTime.now(),
        isArrival: Boolean = true,
        shorttermchanges: Boolean = true,
        modeOfTransport: List<String> = Mode.getAll()
    ): Resource<StopMonitorResponseDto>

    suspend fun getStopByName(
        query: String,
        limit: Int = 15,
        stopsOnly: Boolean = true,
        regionalOnly: Boolean = false,
        stopShortcuts: Boolean = false
    ): Resource<StopFinderResponseDto>


    companion object {
        fun create(): VvoService {
            return VvoServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer( Json {
                            ignoreUnknownKeys = true
                            encodeDefaults = true
                        })
                    }
                }
            )
        }
    }

}