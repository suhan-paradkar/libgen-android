package com.wisestwizard.scigen.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookItemView(title: String, author: String, ext: String, size: String, pages: String, year: String, language: String, onDownloadClick: () -> Unit, onShareClick: () -> Unit ) {
    Column(
        modifier = Modifier.padding(2.dp),
    ) {
        Row(modifier = Modifier.padding(3.dp)) {

            Column {
                Text(text = title, fontSize = 15.sp)
                Text(text = author, fontSize = 14.sp)
            }
            Column(modifier = Modifier.padding(2.dp)) {
                Text(text = ext, Modifier.weight(1f))
                Text(text = size, Modifier.weight(1f))
                Text(text = pages, Modifier.weight(1f))
            }
            Column(Modifier.padding(2.dp)) {
                Text(text = year, Modifier.weight(1f))
                Text(text = language, Modifier.weight(1f))
            }
        }
        Row(modifier = Modifier.weight(9f)) {
            Button(
                onClick = { onDownloadClick() },
                modifier = Modifier.weight(1f),
            ){
                Icon(
                    imageVector = Icons.Rounded.Download,
                    contentDescription = "Download"
                )
            }
            Button(
                onClick = { onShareClick() },
                modifier = Modifier.weight(1f)
            ){
                Icon(
                    imageVector = Icons.Rounded.Share,
                    contentDescription = "Share"
                )
            }
        }
    }
}