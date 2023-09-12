package de.awolf.trip_compose.presentation.stop_monitor_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import de.awolf.trip_compose.presentation.stop_monitor_screen.components.DepartureView
import de.awolf.trip_compose.presentation.stop_monitor_screen.components.StopInfoCard
import java.time.LocalDateTime

@Suppress("UNUSED_VARIABLE")
@Composable
fun StopMonitorScreen(
    viewModel: StopMonitorViewModel,
) {

    val stop = viewModel.stop
    val isStopInfoCardExpanded by viewModel.isStopInfoCardExpanded.collectAsState()
    val isUpdating by viewModel.isUpdating.collectAsState()
    val departures by viewModel.departures.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy((-10).dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        StopInfoCard(
            stop = stop,
            queriedTime = LocalDateTime.now(), //TODO(pass actual queried time)
            isStopInfoCardExpanded = isStopInfoCardExpanded,
            expandStopInfo = viewModel::expandStopInfo
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .zIndex(1f)
                .fillMaxSize(),
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(departures) { departure ->
                DepartureView(departure)
            }
        }
    }
}