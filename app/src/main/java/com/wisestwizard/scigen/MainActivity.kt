package com.wisestwizard.scigen

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import com.wisestwizard.scigen.utils.SearchUtils
import com.wisestwizard.scigen.utils.bookList
import com.wisestwizard.scigen.views.MainLayout

class MainActivity : AppCompatActivity() {
    var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ComposeView(this)
        view.setContent {
            MainLayout(
                onQueryTextSubmit = { SearchUtils().onQueryTextSubmit(query = it, context = this) },
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