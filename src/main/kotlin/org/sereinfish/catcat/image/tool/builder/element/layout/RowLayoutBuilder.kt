package org.sereinfish.catcat.image.tool.builder.element.layout

import org.sereinfish.catcat.image.tool.core.element.layout.Layout
import org.sereinfish.catcat.image.tool.element.layout.RowLayout


fun buildRowLayout(block: RowLayout.() -> Unit): RowLayout{
    val layout = RowLayout()
    layout.block()
    return layout
}

fun Layout.row(block: RowLayout.() -> Unit){
    add(buildRowLayout(block))
}