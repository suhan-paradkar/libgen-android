package com.viveksb007.libgenio.filter

import org.jsoup.nodes.Element
import java.util.function.Predicate

class BookElementFilter : Predicate<Element> {
    override fun test(element: Element): Boolean {
        return "top" == element.attr("valign") && ("#C6DEFF" == element.attr("bgcolor") || "" == element.attr(
            "bgcolor"
        ))
    }
}