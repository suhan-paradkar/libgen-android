package com.wisestwizard.scigen.model

import android.util.Log

class Book {
    var id: String? = null
    var title: String? = null
    var author: String? = null
    var publisher: String? = null
    var year: String? = null
    var pages: String? = null
    var language: String? = null
    var size: String? = null
    var extension: String? = null
    var downloadLink: String? = null
    fun logBook() {
        Log.v(
            TAG, """
     ID : $id
     Title : $title
     Author : $author
     Publisher : $publisher
     Year : $year
     Pages : $pages
     Language : $language
     Size : $size
     Extension : $extension
     Download Link : $downloadLink
     
     """.trimIndent()
        )
    }

    companion object {
        private const val TAG = "Book"
    }
}