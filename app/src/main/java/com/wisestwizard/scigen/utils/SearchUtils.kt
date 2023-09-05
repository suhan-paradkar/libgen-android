package com.wisestwizard.scigen.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.wisestwizard.scigen.BookExtractor
import com.wisestwizard.scigen.model.Book
import org.jsoup.Jsoup
import java.io.IOException


val bookList: MutableList<Book> = ArrayList()
class SearchUtils {

    fun onQueryTextSubmit(query: String, context: Context): Unit {
        findBooks(query, context)
    }

    private fun findBooks(query: String, context: Context) {
        var BASE_URL = "http://libgen.rs/search.php?req="
        Thread {
            try {
                bookList.clear()
                val doc = Jsoup.connect(BASE_URL + query).get()
                bookList.addAll(BookExtractor().extractBooksFromDocument(doc))
                Handler(Looper.getMainLooper()).post {
                    if (bookList.size == 0) {
                        Toast.makeText(
                            context,
                            "Nothing Found.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        updateListView(bookList)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }
    fun updateListView(MutableList: MutableList<Book>) {

    }

}