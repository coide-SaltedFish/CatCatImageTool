package org.sereinfish.catcat.image.tool.builder.element.layout

import org.sereinfish.catcat.image.tool.core.element.layout.Layout
import org.sereinfish.catcat.image.tool.element.layout.ColumnLayout

fun buildColumnLayout(block: ColumnLayout.() -> Unit): ColumnLayout{
    val layout = ColumnLayout()
    layout.block()
    return layout
}

fun Layout.column(block: ColumnLayout.() -> Unit){
    add(buildColumnLayout(block))
}