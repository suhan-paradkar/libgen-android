package com.wisestwizard.scigen.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.More
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookCardView(){
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(2.dp)) {
            Text(text = "Book Title", Modifier.fillMaxWidth(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Row() {
                Text(text = "Author", fontSize = 15.sp)
                Text(text = "Year")
            }
            Row {
                Text(text = "Extension")
                Text(text = "Size")
                Text(text = "Language")
                Text(text ="Pages")
            }
        }
        Button(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Rounded.More,
                contentDescription = "Overflow menu"
            )
        }
    }
}