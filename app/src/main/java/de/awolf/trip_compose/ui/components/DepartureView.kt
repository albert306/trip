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
import de.awolf.trip_compose.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        Surface(
//            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DepartureView(lineNumber = "9", direction = "Prohlis", sheduledTime = "09:41", eta = 6)
        }
    }
}

@Suppress("UNUSED_PARAMETER") // hardcoded version doesnt use all potential parameters
@Composable
fun DepartureView(
    lineNumber: String,
    direction: String,
    platform: String? = "-1",
    mode: String? = "-1",
    eta: Int,
    sheduledTime: String,
    realTime: String? = sheduledTime,
    stateDescription: String = "pünktlich",
    routeChanges: List<String>? = null,
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
                text = lineNumber,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                fontWeight = FontWeight(500),
                modifier = Modifier
                    .background(color = Color.Red, shape = RoundedCornerShape(4.dp))
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
                    text = direction,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = eta.toString() + " min",
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
                    text = sheduledTime,
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
                Text(
                    text = stateDescription,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight(200),
//                    modifier = Modifier.padding(horizontal = 8.dp),
                )
                Text(
                    text = sheduledTime,
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