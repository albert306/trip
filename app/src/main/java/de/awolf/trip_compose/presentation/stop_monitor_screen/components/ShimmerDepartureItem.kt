package de.awolf.trip_compose.presentation.stop_monitor_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.awolf.trip_compose.presentation.helper.shimmerEffect
import de.awolf.trip_compose.ui.theme.AppTheme

@Preview
@Composable
fun Prev() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                ShimmerDepartureItem()
                ShimmerDepartureItem()
            }
        }
    }
}

@Composable
fun ShimmerDepartureItem() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .width(60.dp)
        ) {
            Box(
                // line number
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 0.dp)
                    .size(width = 26.dp, height = 20.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .shimmerEffect()
                    .align(Alignment.CenterStart)
            )
        }
        Column(
            modifier = Modifier
                .padding(end = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            ) {
                Box(
                    // line direction
                    modifier = Modifier
                        .size(width = 160.dp, height = 14.dp)
                        .clip(RoundedCornerShape(1.dp))
                        .shimmerEffect()
                        .align(Alignment.CenterStart)
                )
                Box(
                    // departure eta
                    modifier = Modifier
                        .size(width = 60.dp, height = 14.dp)
                        .clip(RoundedCornerShape(1.dp))
                        .shimmerEffect()
                        .align(Alignment.CenterEnd)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
            ) {
                Box(
                    // sheduled time + departure state
                    modifier = Modifier
                        .size(width = 90.dp, height = 10.dp)
                        .clip(RoundedCornerShape(1.dp))
                        .shimmerEffect()
                        .align(Alignment.CenterStart)
                )
                Box(
                    // actual time
                    modifier = Modifier
                        .size(width = 30.dp, height = 10.dp)
                        .clip(RoundedCornerShape(1.dp))
                        .shimmerEffect()
                        .align(Alignment.CenterEnd)
                )
            }

        }
    }
}