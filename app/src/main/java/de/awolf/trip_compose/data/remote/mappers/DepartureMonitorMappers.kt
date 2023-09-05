package de.awolf.trip_compose.data.remote.mappers

import de.awolf.trip_compose.data.remote.dto.stop_monitor.DepartureDto
import de.awolf.trip_compose.data.remote.dto.stop_monitor.StopMonitorResponseDto
import de.awolf.trip_compose.data.remote.dto.stop_monitor.DivaDto
import de.awolf.trip_compose.data.remote.dto.stop_monitor.PlatformDto
import de.awolf.trip_compose.data.remote.dto.StatusDto
import de.awolf.trip_compose.data.remote.dto.stop_finder.StopFinderResponseDto
import de.awolf.trip_compose.domain.models.Departure
import de.awolf.trip_compose.domain.models.DeparturesWrapper
import de.awolf.trip_compose.domain.models.Diva
import de.awolf.trip_compose.domain.models.Mode
import de.awolf.trip_compose.domain.models.Platform
import de.awolf.trip_compose.domain.models.Status
import de.awolf.trip_compose.domain.models.Stop

fun StopMonitorResponseDto.toDeparturesWrapper(): DeparturesWrapper {
    return DeparturesWrapper(
        name = name,
        status = status.toStatus(),
        region = region,
        expirationTime = expirationTime,
        departures = departures.map { it.toDeparture() },
    )
}

fun DepartureDto.toDeparture(): Departure {
    return Departure(
        id = id,
        lineNumber = lineNumber,
        lineDirection = lineDirection,
        platform = platform.toPlatform(),
        mode = Mode.fromString(mode),
        sheduledTime = sheduledTime,
        realTime = realTime,
        state = Departure.State.fromString(state),
        routeChanges = routeChanges,
        diva = diva.toDiva(),
    )
}

fun StatusDto.toStatus(): Status {
    return Status(code, message)
}

fun PlatformDto.toPlatform(): Platform {
    return Platform(type, name)
}

fun DivaDto.toDiva(): Diva {
    return Diva(number, network)
}

@Suppress("UNUSED")
fun StopFinderResponseDto.toListOfStops(): List<Stop> {
    return stops.map {
        val stopData = it.split('|')
        val id = stopData[0]
        val region = when (stopData[2]) {
            "" -> "Dresden"
            else -> stopData[2]
        }
        val name = stopData[3]

        Stop(id, name, region)
    }
}