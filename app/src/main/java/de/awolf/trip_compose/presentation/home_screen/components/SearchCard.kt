package de.awolf.trip_compose.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchCard(
    searchText: String,
    onSearchTextChange: (newText: String) -> Unit,
    onSearchButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 12.dp, start = 12.dp, end = 8.dp)
                .align(Alignment.TopCenter)
        ) {
            BasicTextField(
                value = searchText,
                onValueChange = { newText -> onSearchTextChange(newText) },
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        if (searchText.isBlank()) {
                            Text(
                                text = "Enter stop name",
                                color = Color.Gray,
                                fontSize = 20.sp
                            )
                        }
                    }
                    innerTextField()
                },
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                ),
                modifier = Modifier
                    .weight(1f)
            )
            DeleteTextButton(
                onClick = { onSearchTextChange("") },
                modifier = Modifier
                    .fillMaxHeight()
                    .width(30.dp)
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
        ) {
            Button(
                onClick = { onSearchButtonClick() },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .size(width = 100.dp, height = 30.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "start search",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}