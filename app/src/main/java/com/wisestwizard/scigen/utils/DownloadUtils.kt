package com.wisestwizard.scigen.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.wisestwizard.scigen.model.Book
import org.jsoup.Jsoup
import java.io.File
import java.io.IOException

class DownloadUtils {
    companion object {
        const val TAG = "DownloadUtils"
    }
}
    fun downloadBook(position: Int, bookList: List<Book>, context: Context) {
        val downloadLink = bookList[position].downloadLink
        val fileName = bookList[position].title + '.' + bookList[position].extension
        Thread(Runnable {
            try {
                Log.v(DownloadUtils.TAG, downloadLink?:"null")
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
                    Log.v(DownloadUtils.TAG, "Link not found")
                } else {
                    Log.v(DownloadUtils.TAG, finalDownloadLink)
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
