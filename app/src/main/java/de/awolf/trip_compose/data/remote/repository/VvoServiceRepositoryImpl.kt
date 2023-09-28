package de.awolf.trip_compose.data.remote.repository

import de.awolf.trip_compose.data.remote.HttpRoutes
import de.awolf.trip_compose.data.remote.dto.stop_finder.StopFinderResponseDto
import de.awolf.trip_compose.domain.repository.VvoServiceRepository
import de.awolf.trip_compose.data.remote.dto.stop_monitor.StopMonitorResponseDto
import de.awolf.trip_compose.data.remote.mappers.toStopFinderInfo
import de.awolf.trip_compose.data.remote.mappers.toStopMonitorInfo
import de.awolf.trip_compose.domain.models.StopFinderInfo
import de.awolf.trip_compose.domain.models.StopMonitorInfo
import de.awolf.trip_compose.domain.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class VvoServiceRepositoryImpl(
    private val client: HttpClient
) : VvoServiceRepository {

    override suspend fun monitorStop(
        stopId: String,
        limit: Int,
        time: LocalDateTime,
        isArrival: Boolean,
        shorttermchanges: Boolean,
        modeOfTransport: List<String>
    ): Resource<StopMonitorInfo> {

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

        try {
            val response = client.post {
                url(HttpRoutes.STOP_MONITOR)
                contentType(ContentType.Application.Json)
                setBody(jsonBody)
            }.body<StopMonitorResponseDto>()

            if (!response.responseStatus.isOk()) {
                return Resource.Error(
                    message = "API response bad status\ncode: " +  response.responseStatus.code + "\nmessage: " + response.responseStatus.message
                )
            }

            return Resource.Success(response.toStopMonitorInfo())

        } catch (e: RedirectResponseException) {
            // 3xx - responses
            return Resource.Error("HTTP request failed with message: " + e.response.status.description)
        } catch (e: ClientRequestException) {
            // 4xx - responses
            return Resource.Error("HTTP request failed with message: " + e.response.status.description)
        } catch (e: ServerResponseException) {
            // 5xx - responses
            return Resource.Error("HTTP request failed with message: " + e.response.status.description)
        } catch (e: Exception) {
            return Resource.Error("unknown error (most likely json parse error)")
        }
    }




    override suspend fun getStopByName(
        query: String,
        limit: Int,
        stopsOnly: Boolean,
        regionalOnly: Boolean,
        stopShortcuts: Boolean
    ): Resource<StopFinderInfo> {

        val jsonBody = JsonObject(
            mapOf(
                "query" to JsonPrimitive(query),
                "limit" to JsonPrimitive(limit),
                "stopsOnly" to JsonPrimitive(stopsOnly),
                "regionalOnly" to JsonPrimitive(regionalOnly),
                "stopShortcuts" to JsonPrimitive(stopShortcuts),
            )
        )

        try {
            val response = client.post {
                url(HttpRoutes.STOP_FINDER)
                contentType(ContentType.Application.Json)
                setBody(jsonBody)
            }.body<StopFinderResponseDto>()

            if (response.pointStatus == "NotIdentified") {
                return Resource.Error(
                    message = "Stop could not be identified"
                )
            }

            if (!response.responseStatus.isOk()) {
                return Resource.Error(
                    message = "API response bad status\ncode: " +  response.responseStatus.code + "\nmessage: " + response.responseStatus.message
                )
            }

            return Resource.Success(response.toStopFinderInfo())

        } catch (e: RedirectResponseException) {
            // 3xx - responses
            return Resource.Error("HTTP request failed with message: " + e.response.status.description)
        } catch (e: ClientRequestException) {
            // 4xx - responses
            return Resource.Error("HTTP request failed with message: " + e.response.status.description)
        } catch (e: ServerResponseException) {
            // 5xx - responses
            return Resource.Error("HTTP request failed with message: " + e.response.status.description)
        } catch (e: Exception) {
            return Resource.Error("unknown error (most likely json parse error)")
        }
    }
}