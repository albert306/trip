package de.awolf.trip_compose.presentation.stop_monitor_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.awolf.trip_compose.domain.models.Departure
import de.awolf.trip_compose.domain.models.Diva
import de.awolf.trip_compose.domain.models.Mode
import de.awolf.trip_compose.domain.models.Platform
import de.awolf.trip_compose.ui.theme.AppTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.absoluteValue

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        Surface(
//            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Example TimeStamps
            val realTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(1693583700000),
                ZoneId.systemDefault()
            )

            val sheduledTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(1693583160000),
                ZoneId.systemDefault()
            )
            Column() {
                DepartureView(Departure(id = "", lineNumber = "360", lineDirection = "Prohlis", mode = Mode.PLUSBUS, sheduledTime = sheduledTime, realTime = realTime, departureState = Departure.DepartureState.DELAYED, platform = Platform("track", "8"), diva = Diva("9", "vvo"), routeChanges = emptyList()))
                DepartureView(Departure(id = "", lineNumber = "9", lineDirection = "Prohlis", mode = Mode.TRAM, sheduledTime = sheduledTime, realTime = realTime, departureState = Departure.DepartureState.DELAYED, platform = Platform("track", "8"), diva = Diva("9", "vvo"), routeChanges = emptyList()))
            }
        }
    }
}

@Composable
fun DepartureView(
    departure: Departure
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .widthIn(
                    min = 58.dp,
                    max = 100.dp
                )
        ) {
            Text(
                text = departure.lineNumber,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                fontWeight = FontWeight(500),
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 0.dp)
                    .background(color = departure.mode.getColor(), shape = RoundedCornerShape(2.dp))
                    .padding(horizontal = 4.dp, vertical = 0.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(end = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = departure.lineDirection,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = departure.getETA().toString() + " min",
                    textAlign = TextAlign.End,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.width(90.dp),
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = departure.sheduledTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight(300),
                )
                Text(
                    text = "•",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight(300),
                    modifier = Modifier.padding(horizontal = 8.dp),
                )

                val delay = departure.getDelay()
                var departureStateDescription = "pünktlich"
                var departureStateDescriptionColor = Color.Green
                if (delay > 0) {
                    departureStateDescription = "+ " + delay.toString()
                    departureStateDescriptionColor = Color.Red
                }
                if (delay < 0) {
                    departureStateDescription =  "- " + delay.absoluteValue.toString()
                    departureStateDescriptionColor = Color.Blue
                }

                Text(
                    text = departureStateDescription,
                    fontSize = 14.sp,
                    color = departureStateDescriptionColor,
                    fontWeight = FontWeight(300),
//                    modifier = Modifier.padding(horizontal = 8.dp),
                )
                Text(
                    text = departure.realTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                    textAlign = TextAlign.End,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight(300),
                    modifier = Modifier.weight(1f),
                )
            }

        }
    }

}