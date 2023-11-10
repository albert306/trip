package de.awolf.trip_compose.presentation.stop_monitor_screen.components

import androidx.compose.animation.AnimatedVisibility
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
import de.awolf.trip_compose.domain.models.Mode
import de.awolf.trip_compose.domain.models.Platform
import de.awolf.trip_compose.domain.models.StopScheduleItem
import de.awolf.trip_compose.presentation.helper.clickableWithoutRipple
import de.awolf.trip_compose.ui.theme.AppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.absoluteValue

@Preview()
@Composable
fun DepartureViewPreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            DepartureView(
                departure = Departure(
                    id = "",
                    dlId = "",
                    lineNumber = "66",
                    lineDirection = "Mockritz",
                    platform = Platform(type = "track", name = "2"),
                    mode = Mode.CITYBUS,
                    sheduledTime = LocalDateTime.now(),
                    realTime = LocalDateTime.now(),
                    departureState = Departure.DepartureState.INTIME,
                    routeChanges = emptyList(),
                    diva = null,
                    isShowingDetailedStopSchedule = true,
                    detailedStopSchedule = listOf(
                        StopScheduleItem("", "", "Dresden", "Münzteichweg", "Next", Platform(type = "track", name = "2"), LocalDateTime.now().plusMinutes(2)),
                        StopScheduleItem("", "", "Dresden", "Münzteichweg", "Next", Platform(type = "track", name = "2"), LocalDateTime.now().plusMinutes(2)),
                        StopScheduleItem("", "", "Dresden", "Münzteichweg", "Next", Platform(type = "track", name = "2"), LocalDateTime.now().plusMinutes(2)),
                        StopScheduleItem("", "", "Dresden", "Münzteichweg", "Next", Platform(type = "track", name = "2"), LocalDateTime.now().plusMinutes(2)),
                    )
                ),
                onClick = {})
        }
    }
}

@Composable
fun DepartureView(
    departure: Departure,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .clickableWithoutRipple {
                    onClick()
                }
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
                        .background(
                            color = departure.mode.getColor(),
                            shape = RoundedCornerShape(2.dp)
                        )
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
                        departureStateDescription = "- " + delay.absoluteValue.toString()
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
        AnimatedVisibility(visible = departure.isShowingDetailedStopSchedule && departure.detailedStopSchedule != null) {
            Column {
                for (detailedStop in departure.detailedStopSchedule!!) {
                    StopScheduleItemView(stopScheduleItem = detailedStop)
                }
            }
        }
    }
}