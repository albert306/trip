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
import de.awolf.trip_compose.presentation.helper.clickableWithoutRipple
import de.awolf.trip_compose.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        Surface(
//            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            StopView(stop = Stop("33000028", "Haupbahnhof", "Dresden"), {}, {})
        }
    }
}

@Composable
fun StopView(
    stop: Stop,
    onFavouriteStarClick: () -> Unit,
    onNameClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Default: no favourite
    var icon = painterResource(id = R.drawable.baseline_star_outline_24)
    var description = "outlined star"

    if (stop.isFavourite) {
        // if favourite
        icon = painterResource(id = R.drawable.baseline_star_24)
        description = "filled star"
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(46.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = description,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(26.dp)
                    .clickableWithoutRipple {
                        onFavouriteStarClick()
                    }
            )
        }
        Column(
            modifier = Modifier
                .clickableWithoutRipple {
                    onNameClick()
                }
                .weight(1f)
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