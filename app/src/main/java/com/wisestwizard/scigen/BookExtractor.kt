package com.wisestwizard.scigen

import com.wisestwizard.scigen.filter.BookElementFilter
import com.wisestwizard.scigen.model.Book
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.util.Optional
import java.util.stream.Collectors

internal class BookExtractor {
    fun extractBooksFromDocument(document: Document?): MutableList<Book> {
        return Optional.ofNullable(document)
            .map { doc: Document -> doc.getElementsByTag("tr") }
            .map { elements: Elements ->
                elements
                    .stream()
                    .filter(BookElementFilter())
                    .map { obj: Element -> obj.children() }
                    .map { bookElements: Elements -> getBookFromBookElement(bookElements) }
                    .collect(Collectors.toList())
            }
            .orElseGet { ArrayList() }
    }

    private fun getBookFromBookElement(bookElements: Elements): Book {
        val book = Book()
        book.id = bookElements[0].text()
        val authors = bookElements[1].children()
        val author = StringBuilder()
        for (authorElements in authors) {
            author.append(authorElements.text())
            author.append(", ")
        }
        author.setLength(author.length - 2)
        book.author = author.toString()
        book.title = bookElements[2].children()[0].text()
        book.publisher = bookElements[3].text()
        book.year = bookElements[4].text()
        book.pages = bookElements[5].text()
        book.language = bookElements[6].text()
        book.size = bookElements[7].text()
        book.extension = bookElements[8].text()
        book.downloadLink = bookElements[9].children()[0].attr("href")
        return book
    }
}