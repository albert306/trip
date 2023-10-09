package de.awolf.trip_compose.data.remote.mappers

import de.awolf.trip_compose.data.remote.dto.stop_monitor.DepartureDto
import de.awolf.trip_compose.data.remote.dto.stop_monitor.StopMonitorResponseDto
import de.awolf.trip_compose.data.remote.dto.stop_finder.StopFinderResponseDto
import de.awolf.trip_compose.domain.models.Departure
import de.awolf.trip_compose.domain.models.StopMonitorInfo
import de.awolf.trip_compose.domain.models.Mode
import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.domain.models.StopFinderInfo

fun StopMonitorResponseDto.toStopMonitorInfo(): StopMonitorInfo {
    return StopMonitorInfo(
        name = name,
        responseStatus = responseStatus,
        region = region,
        expirationTime = expirationTime,
        departures = departures.map { it.toDeparture() },
    )
}

fun DepartureDto.toDeparture(): Departure {
    return Departure(
        id = id,
        dlId = dlId,
        lineNumber = lineNumber,
        lineDirection = lineDirection,
        platform = platform,
        mode = Mode.fromString(mode),
        sheduledTime = sheduledTime,
        realTime = realTime,
        departureState = Departure.DepartureState.fromString(state),
        routeChanges = routeChanges,
        diva = diva,
    )
}

fun StopFinderResponseDto.toStopFinderInfo(): StopFinderInfo {
    return StopFinderInfo(
        responseStatus = responseStatus,
        pointStatus = pointStatus,
        stops = stops.map {
            val stopData = it.split('|')
            val id = stopData[0]
            val region = when (stopData[2]) {
                "" -> "Dresden"
                else -> stopData[2]
            }
            val name = stopData[3]

            Stop(id, name, region)
        },
        expirationTime = expirationTime,
    )
}