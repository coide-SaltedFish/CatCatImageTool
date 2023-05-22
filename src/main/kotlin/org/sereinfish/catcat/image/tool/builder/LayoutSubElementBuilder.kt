package org.sereinfish.catcat.image.tool.builder

import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.core.layout.Layout

open class LayoutSubElementBuilder(
    val layout: Layout
) {
    operator fun Element.unaryPlus(){
        this@LayoutSubElementBuilder.add(this)
    }

    fun add(element: Element){
        layout.add(element)
    }
}