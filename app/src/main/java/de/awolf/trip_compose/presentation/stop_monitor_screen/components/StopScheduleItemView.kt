package de.awolf.trip_compose.presentation.stop_monitor_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.awolf.trip_compose.domain.models.Platform
import de.awolf.trip_compose.domain.models.StopScheduleItem
import de.awolf.trip_compose.ui.theme.AppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Preview(showBackground = true)
@Composable
private fun StopScheduleItemViewPreview() {
    AppTheme {
        Surface(
//            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            StopScheduleItemView(StopScheduleItem("", "", "Dresden", "Münzteichweg", "Next", Platform(type = "track", name = "2"), LocalDateTime.now().plusMinutes(2)))
            StopScheduleItemView(StopScheduleItem("", "", "Dresden", "Münzteichweg", "Next", Platform(type = "track", name = "2"), LocalDateTime.now().plusMinutes(2)))
        }
    }
}

@Composable
fun StopScheduleItemView(
    stopScheduleItem: StopScheduleItem,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp)
    ) {

        Spacer(Modifier.width(58.dp))

        Text(
            text = stopScheduleItem.stopName,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight(400),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = stopScheduleItem.arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm")),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            fontWeight = FontWeight(200)
        )
    }

}