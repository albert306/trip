package de.awolf.trip_compose.data.remote

import de.awolf.trip_compose.domain.models.Mode
import de.awolf.trip_compose.data.remote.dto.DepartureMonitorDto
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

    suspend fun postDepartureMonitor(
        stopId: String,
        limit: Int = 5,
        time: LocalDateTime = LocalDateTime.now(),
        isArrival: Boolean = true,
        shorttermchanges: Boolean = true,
        modeOfTransport: List<String> = Mode.getAll()
    ): Resource<DepartureMonitorDto>

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