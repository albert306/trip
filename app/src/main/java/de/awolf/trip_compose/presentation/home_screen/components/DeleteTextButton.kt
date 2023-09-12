package de.awolf.trip_compose.presentation.home_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DeleteTextButton(
    onClick: (query: String) -> Unit,
    size: Dp = 24.dp
) {
    TextButton(
        onClick = { onClick("") },
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .size(size)
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "delete text",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(size)
        )
    }
}