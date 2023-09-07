package com.wisestwizard.scigen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.wisestwizard.scigen.model.Book
import com.wisestwizard.scigen.views.ui.theme.LibgenandroidTheme
import com.wisestwizard.scigen.widget.BookListWidget
import com.wisestwizard.scigen.widget.SearchBarWidget

@Composable
fun MainLayout(
    onQueryTextSubmit: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithOverflowMenu()
        }
    ) {
        Surface(
            modifier = Modifier.padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen(
                onQueryTextSubmit = onQueryTextSubmit
            )
        }
    }

}

@Composable
fun MainScreen(
    onQueryTextSubmit: (String) -> Unit,
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        SearchBarWidget(
            onQueryTextSubmit = onQueryTextSubmit
        )
        BookListWidget(context = context)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithOverflowMenu(){
    TopAppBar(
        title = {
            Text(text = "SciGen")
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Menu"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onTertiary,
            titleContentColor = MaterialTheme.colorScheme.tertiary,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.secondary
        ),
    )
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    LibgenandroidTheme {
        MainLayout(
            onQueryTextSubmit = { })
    }
}
