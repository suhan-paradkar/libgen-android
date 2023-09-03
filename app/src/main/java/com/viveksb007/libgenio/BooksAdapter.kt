package com.viveksb007.libgenio

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.viveksb007.libgenio.BooksAdapter.MyViewHolder
import com.viveksb007.libgenio.model.Book
import com.viveksb007.libgenio.R
import org.jsoup.Jsoup
import java.io.File
import java.io.IOException

class BooksAdapter(private val context: Context, private val bookList: List<Book>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.book_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = bookList[position]
        holder.title.text = book.title
        holder.author.text = "By : " + book.author
        holder.fileSize.text = "Size : " + book.size
        holder.fileExtension.text = "Format : " + book.extension
        holder.pages.text = "Pages : " + book.pages
        holder.language.text = "Language : " + book.language
        holder.year.text = "Year : " + book.year
        holder.overflowButton.setOnClickListener { view: View? ->
            showPopupMenu(
                holder.overflowButton,
                position
            )
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    private fun showPopupMenu(view: View, position: Int) {
        val popup = PopupMenu(
            context, view
        )
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.book_menu, popup.menu)
        val itemClickListener = MyMenuItemClickListener(position)
        popup.setOnMenuItemClickListener(itemClickListener)
        popup.show()
    }

    internal inner class MyMenuItemClickListener(private val position: Int) :
        PopupMenu.OnMenuItemClickListener {
        override fun onMenuItemClick(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.action_download -> {
                    Toast.makeText(context, "Download Book", Toast.LENGTH_SHORT).show()
                    downloadBook(position)
                    return true
                }

                R.id.action_share -> {
                    Toast.makeText(context, "Share Book Link", Toast.LENGTH_SHORT).show()
                    shareLink(position)
                    return true
                }
            }
            return false
        }
    }

    private fun shareLink(position: Int) {}
    private fun downloadBook(position: Int) {
        val downloadLink = bookList[position].downloadLink
        val fileName = bookList[position].title + '.' + bookList[position].extension
        Thread(Runnable {
            try {
                Log.v(TAG, downloadLink?:"null")
                val doc = Jsoup.connect(downloadLink).get()
                if (doc == null) {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(
                            context, "Error in fetching data.", Toast.LENGTH_SHORT
                        ).show()
                    }
                    return@Runnable
                }
                val elements = doc.getElementsByTag("td")
                var finalDownloadLink = ""
                for (element in elements) {
                    if ("center" == element.attr("align") && "top" == element.attr("valign")) {
                        finalDownloadLink = element.children()[0].attr("href")
                        break
                    }
                }
                if ("" == finalDownloadLink) {
                    Log.v(TAG, "Link not found")
                } else {
                    Log.v(TAG, finalDownloadLink)
                    val downloadManager =
                        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    val downloadRequest = DownloadManager.Request(Uri.parse(finalDownloadLink))
                    val destinationFile = File(Environment.getExternalStorageDirectory(), fileName)
                    downloadRequest.setDescription("Downloading Book")
                    downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    downloadRequest.setDestinationUri(Uri.fromFile(destinationFile))
                    assert(downloadManager != null)
                    downloadManager.enqueue(downloadRequest)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }).start()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var author: TextView
        var pages: TextView
        var fileExtension: TextView
        var fileSize: TextView
        var language: TextView
        var year: TextView
        var overflowButton: ImageButton

        init {
            title = view.findViewById<TextView>(R.id.tv_title)
            author = view.findViewById<TextView>(R.id.tv_author)
            pages = view.findViewById<TextView>(R.id.tv_pages)
            fileExtension = view.findViewById<TextView>(R.id.tv_file_extension)
            fileSize = view.findViewById<TextView>(R.id.tv_file_size)
            overflowButton = view.findViewById<ImageButton>(R.id.overflow_button)
            language = view.findViewById<TextView>(R.id.tv_language)
            year = view.findViewById<TextView>(R.id.tv_year)
        }
    }

    companion object {
        private const val TAG = "BooksAdapter"
    }
}