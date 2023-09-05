package com.wisestwizard.scigen

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.wisestwizard.scigen.utils.SearchUtils
import com.wisestwizard.scigen.utils.bookList
import com.wisestwizard.scigen.views.MainLayout

class MainActivity : AppCompatActivity() {
    var active = true
    var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ComposeView(this)
        view.setContent {
            MainLayout(
            modifier = Modifier.padding(16.dp),
            query = "",
            onQueryChange = { SearchUtils().onQueryTextSubmit(query = it, context = this) },
            onQueryTextSubmit = { SearchUtils().onQueryTextSubmit(query = it, context = this)
                                active = false},
            setActiveStatus = { active },
            itemList = bookList
            )
        }
        setContentView(view)
    }


@Deprecated("This method is deprecated")
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}