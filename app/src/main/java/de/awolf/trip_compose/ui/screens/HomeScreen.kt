package de.awolf.trip_compose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.awolf.trip_compose.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun Preview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen()
        }
    }
}


@Composable
fun HomeScreen() {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp))
                .background(MaterialTheme.colorScheme.surface)
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
                    onValueChange = { searchText = it },
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
//                        .fillMaxHeight()
                        .weight(1f)
                )
                TextButton(
                    onClick = { searchText = "" },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(30.dp)

                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "delete text",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
            ) {
                Button(
                    onClick = { /* Start search */ },
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

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(20) {
                Text(
                    text = "<stop-name>",
                    modifier = Modifier
                        .padding(12.dp)
                )
            }
        }
    }
}