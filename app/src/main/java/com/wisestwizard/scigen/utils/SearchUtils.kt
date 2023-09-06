package com.wisestwizard.scigen.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.wisestwizard.scigen.BookExtractor
import com.wisestwizard.scigen.model.Book
import org.jsoup.Jsoup
import java.io.IOException

class SearchUtils {

    fun onQueryTextSubmit(query: String, context: Context, bookList: MutableList<Book>): Unit {
        findBooks(query, context, bookList)
    }

    private fun findBooks(query: String, context: Context, bookList: MutableList<Book>) {
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
                        Toast.makeText(
                            context,
                            "Found " + bookList.size + " books.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }
}