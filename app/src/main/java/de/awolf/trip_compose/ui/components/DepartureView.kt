package de.awolf.trip_compose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
            DepartureView(Departure(id = "", lineNumber = "9", lineDirection = "Prohlis", mode = Mode.TRAM, sheduledTime = sheduledTime, realTime = realTime, state = Departure.State.DELAYED, platform = Platform("track", "8"), diva = Diva("9", "vvo"), routeChanges = emptyList()))
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
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(40.dp)
            ) {
            Text(
                text = departure.lineNumber,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                fontWeight = FontWeight(500),
                modifier = Modifier
                    .background(color = departure.mode.getColor(), shape = RoundedCornerShape(4.dp))
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
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = departure.getETA().toString() + " min",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    fontWeight = FontWeight(400),
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = departure.sheduledTime.toString(),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight(200),
                )
                Text(
                    text = "•",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight(50),
                    modifier = Modifier.padding(horizontal = 8.dp),
                )

                val delay = departure.getDelay()
                var stateDescription = "pünktlich"
                var stateDescriptionColor = Color.Green
                if (delay > 0) {
                    stateDescription = "+ " + delay.toString()
                    stateDescriptionColor = Color.Red
                }
                if (delay < 0) {
                    stateDescription = delay.toString()
                    stateDescriptionColor = Color.Blue
                }

                Text(
                    text = stateDescription,
                    fontSize = 14.sp,
                    color = stateDescriptionColor,
                    fontWeight = FontWeight(200),
//                    modifier = Modifier.padding(horizontal = 8.dp),
                )
                Text(
                    text = departure.realTime.toString(),
                    textAlign = TextAlign.End,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight(200),
                    modifier = Modifier.weight(1f),
                )
            }

        }
    }

}