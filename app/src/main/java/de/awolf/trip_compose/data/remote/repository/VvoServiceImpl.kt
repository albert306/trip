package de.awolf.trip_compose.data.remote.repository

import de.awolf.trip_compose.data.remote.HttpRoutes
import de.awolf.trip_compose.data.remote.dto.stop_finder.StopFinderResponseDto
import de.awolf.trip_compose.domain.repository.VvoService
import de.awolf.trip_compose.data.remote.dto.stop_monitor.StopMonitorResponseDto
import de.awolf.trip_compose.domain.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class VvoServiceImpl(
    private val client: HttpClient
) : VvoService {

    override suspend fun monitorStop(
        stopId: String,
        limit: Int,
        time: LocalDateTime,
        isArrival: Boolean,
        shorttermchanges: Boolean,
        modeOfTransport: List<String>
    ): Resource<StopMonitorResponseDto> {

        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")

        val jsonBody = JsonObject(
            mapOf(
                "stopid" to JsonPrimitive(stopId),
                "limit" to JsonPrimitive(limit),
                "time" to JsonPrimitive(ZonedDateTime.of(time, ZoneId.of("Europe/Berlin")).format(dateFormat)),
                "isarrival" to JsonPrimitive(isArrival),
                "shorttermchanges" to JsonPrimitive(shorttermchanges),
                "mot" to JsonPrimitive(modeOfTransport.toString())
            )
        )

        return try {
            Resource.Success(client.post {
                url(HttpRoutes.STOP_MONITOR)
                contentType(ContentType.Application.Json)
                body = jsonBody
            })
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            Resource.Error(e.response.status.description)
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            Resource.Error(e.response.status.description)
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            Resource.Error(e.response.status.description)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            Resource.Error("unknown error")
        }
    }

    override suspend fun getStopByName(
        query: String,
        limit: Int,
        stopsOnly: Boolean,
        regionalOnly: Boolean,
        stopShortcuts: Boolean
    ): Resource<StopFinderResponseDto> {

        val jsonBody = JsonObject(
            mapOf(
                "query" to JsonPrimitive(query),
                "limit" to JsonPrimitive(limit),
                "stopsOnly" to JsonPrimitive(stopsOnly),
                "regionalOnly" to JsonPrimitive(regionalOnly),
                "stopShortcuts" to JsonPrimitive(stopShortcuts),
            )
        )

        return try {
            Resource.Success(client.post {
                url(HttpRoutes.STOP_FINDER)
                contentType(ContentType.Application.Json)
                body = jsonBody
            })
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            Resource.Error(e.response.status.description)
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            Resource.Error(e.response.status.description)
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            Resource.Error(e.response.status.description)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            Resource.Error("unknown error")
        }
    }
}