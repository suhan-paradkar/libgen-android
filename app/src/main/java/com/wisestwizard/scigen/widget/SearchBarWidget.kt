package com.wisestwizard.scigen.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWidget(query: String, modifier: Modifier = Modifier, onQueryChange: (String) -> Unit, onQueryTextSubmit: (String) -> Unit, activestatus: () -> Boolean, activeStatus: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SearchBar(
            modifier = Modifier.padding(16.dp),
            query = query,
            onQueryChange = { onQueryChange(query) },
            onSearch = { onQueryTextSubmit(query) },
            active = false,
            onActiveChange = { active -> activeStatus() }
            ){
        }
    }
}
