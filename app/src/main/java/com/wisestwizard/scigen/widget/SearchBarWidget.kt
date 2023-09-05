package com.wisestwizard.scigen.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchBarWidget(modifier: Modifier = Modifier, onQueryChange: (String) -> Unit, onQueryTextSubmit: (String) -> Unit) {
    val query = remember { mutableStateOf("") }
    Surface(
        modifier = Modifier.padding(20.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        TextField(
            value = query.value,
            modifier = Modifier.border(BorderStroke(0.dp, Color.Transparent))
                .fillMaxWidth(),
            onValueChange = { query.value = it; onQueryChange(it) },
            placeholder = {
                SearchHint(text = "Search")
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    modifier = Modifier.clickable {
                        onQueryTextSubmit(query.value)
                    }
                )
            },
            shape = RoundedCornerShape(30.dp),
            enabled = true,
            readOnly = false,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SearchHint(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = Color.Gray
    )
}