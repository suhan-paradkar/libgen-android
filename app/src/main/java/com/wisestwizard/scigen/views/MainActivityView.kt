package com.wisestwizard.scigen.views

import Booklist
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wisestwizard.scigen.model.Book
import com.wisestwizard.scigen.views.ui.theme.LibgenandroidTheme
import com.wisestwizard.scigen.widget.SearchBarWidget

@Composable
fun MainLayout(modifier: Modifier, query: String, onQueryChange: (String) -> Unit, onQueryTextSubmit: (String) -> Unit, setActiveStatus: () -> Boolean, itemList: MutableList<Book> = ArrayList()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Mainscreen(query = query, modifier = modifier, onQueryChange = onQueryChange,onQueryTextSubmit = onQueryTextSubmit, setActivestatus = setActiveStatus, itemList = itemList)
    }
}

@Composable
fun Mainscreen(query: String, modifier: Modifier = Modifier, onQueryChange: (String) -> Unit, onQueryTextSubmit: (String) -> Unit, setActivestatus: () -> Boolean, itemList: MutableList<Book> = ArrayList()) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        SearchBarWidget(
            query = query,
            onQueryChange = onQueryChange,
            onQueryTextSubmit = onQueryTextSubmit,
            activestatus = { setActivestatus() },
            ){}
        Booklist(itemList = itemList, context = context)
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    LibgenandroidTheme {
        Mainscreen(query = "Hello", modifier = Modifier, onQueryTextSubmit = { }, onQueryChange = { }, setActivestatus = { false })
    }
}