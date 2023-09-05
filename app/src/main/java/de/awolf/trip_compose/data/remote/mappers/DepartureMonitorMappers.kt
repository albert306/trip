package de.awolf.trip_compose.data.remote.mappers

import de.awolf.trip_compose.data.remote.dto.DepartureInfoDto
import de.awolf.trip_compose.data.remote.dto.DepartureMonitorDto
import de.awolf.trip_compose.data.remote.dto.DivaDto
import de.awolf.trip_compose.data.remote.dto.PlatformDto
import de.awolf.trip_compose.data.remote.dto.StatusDto
import de.awolf.trip_compose.domain.models.Departure
import de.awolf.trip_compose.domain.models.DeparturesWrapper
import de.awolf.trip_compose.domain.models.Diva
import de.awolf.trip_compose.domain.models.Mode
import de.awolf.trip_compose.domain.models.Platform
import de.awolf.trip_compose.domain.models.Status

fun DepartureMonitorDto.toDeparturesWrapper(): DeparturesWrapper {
    return DeparturesWrapper(
        name = name,
        status = status.toStatus(),
        region = region,
        expirationTime = expirationTime,
        departures = departures.map { it.toDeparture() },
    )
}

fun DepartureInfoDto.toDeparture(): Departure {
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