package de.awolf.trip_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import de.awolf.trip_compose.data.remote.VvoService
import de.awolf.trip_compose.data.remote.mappers.toDeparturesWrapper
import de.awolf.trip_compose.domain.models.DeparturesWrapper
import de.awolf.trip_compose.domain.util.Resource
import de.awolf.trip_compose.ui.theme.AppTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {

    private val vvoService = VvoService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val departures = produceState<DeparturesWrapper?>(
                initialValue = null,
                producer = {
                    value = when (val result = vvoService.postDepartureMonitor(
                            stopId = "33000028",
                            time = LocalDateTime.now().plusHours(8)
                        )) {
                            is Resource.Success -> {
                                result.data?.toDeparturesWrapper()
                            }
                            is Resource.Error -> {
                                null
                            }
                        }
                }
            )

            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text(text = departures.value.toString())
                }
            }
        }
    }
}