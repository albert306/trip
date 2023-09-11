package de.awolf.trip_compose.presentation.home_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.awolf.trip_compose.R
import de.awolf.trip_compose.domain.models.Stop
import de.awolf.trip_compose.presentation.util.clickableWithoutRipple
import de.awolf.trip_compose.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        Surface(
//            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            StopView(stop = Stop("33000028", "Haupbahnhof", "Dresden"), stopIsFavourite = false)
        }
    }
}

@Composable
fun StopView(
    stop: Stop,
    stopIsFavourite: Boolean,
) {
    // Default: no favourite
    var icon = painterResource(id = R.drawable.baseline_star_outline_24)
    var description = "outlined star"

    if (stopIsFavourite) {
        // if favourite
        icon = painterResource(id = R.drawable.baseline_star_24)
        description = "filled star"
    }

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
            Icon(
                painter = icon,
                contentDescription = description,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(24.dp)
            )
        }
        Column(
            modifier = Modifier
                .clickableWithoutRipple {
                    println("stop ${stop.name} was clicked")
                }
        ) {
            Text(
                text = stop.name,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                fontWeight = FontWeight(400),
            )
            Text(
                text = stop.region,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                fontWeight = FontWeight(200)
            )
        }
    }

}