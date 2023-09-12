package de.awolf.trip_compose.presentation.stop_monitor_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.presentation.helper.clickableWithoutRipple
import java.time.LocalDateTime

@Composable
fun StopInfoCard(
    stop: Stop,
    queriedTime: LocalDateTime,
    isStopInfoCardExpanded: Boolean,
    expandStopInfo: () -> Unit
) {

    Column(
        modifier = Modifier
            .zIndex(2f)
            .fillMaxWidth()
            .clickableWithoutRipple { expandStopInfo() }
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = stop.name,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            fontWeight = FontWeight(400),
            modifier = Modifier
        )
        AnimatedVisibility(
            visible = isStopInfoCardExpanded
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
            ) {
                Text(
                    text = stop.region,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight(200),
                )
                Text(
                    text = "•",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight(200),
                    modifier = Modifier.padding(horizontal = 8.dp),
                )
                Text(
                    text = queriedTime.toString(),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight(200),
                )
            }
        }
    }
}