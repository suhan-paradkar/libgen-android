package com.wisestwizard.scigen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wisestwizard.scigen.model.Book
import com.wisestwizard.scigen.databinding.ActivityMainBinding
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val BASE_URL = "http://libgen.rs/search.php?req="
    private val bookList: MutableList<Book> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private var booksAdapter: BooksAdapter? = null
    var doubleBackToExitPressedOnce = false
    private var progressBar: ProgressBar? = null
    private var searchView: SearchView? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val recyclerView = findViewById<RecyclerView>(R.id.book_recycler_view)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        booksAdapter = BooksAdapter(this, bookList)
        recyclerView.setAdapter(booksAdapter)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar_cyclic)
        progressBar.setVisibility(View.GONE)
        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setIconifiedByDefault(false)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                progressBar.setVisibility(View.VISIBLE)
                findBooks(query)
                progressBar.setVisibility(View.GONE)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun findBooks(query: String) {
        Thread {
            try {
                bookList.clear()
                val doc = Jsoup.connect(BASE_URL + query).get()
                bookList.addAll(BookExtractor().extractBooksFromDocument(doc))
                Handler(Looper.getMainLooper()).post {
                    if (bookList.size == 0) Toast.makeText(
                        this@MainActivity,
                        "Nothing Found.",
                        Toast.LENGTH_SHORT
                    ).show() else {
                        updateListView()
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun updateListView() {
        booksAdapter!!.notifyDataSetChanged()
        Log.v(TAG, bookList.size.toString() + "")
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}