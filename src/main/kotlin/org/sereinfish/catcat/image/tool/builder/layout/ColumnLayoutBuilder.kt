package org.sereinfish.catcat.image.tool.builder.layout

import org.sereinfish.catcat.image.tool.builder.ElementBuilder
import org.sereinfish.catcat.image.tool.builder.LayoutSubElementBuilder
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.layout.ColumnLayout

class ColumnLayoutBuilder(
    val layout: ColumnLayout = ColumnLayout(),
): ElementBuilder(layout) {
    var alignment: Alignment
        get() = layout.alignment
        set(value) { layout.alignment = value }

    fun build() = layout
}

fun columnLayoutBuilder(
    buildBlock: ColumnLayoutBuilder.() -> Unit = {},
    block: LayoutSubElementBuilder.() -> Unit
): ColumnLayout{
    val builder = ColumnLayoutBuilder()
    builder.buildBlock()
    val subBuilder = LayoutSubElementBuilder(builder.layout)
    subBuilder.block()
    return builder.build()
}

fun LayoutSubElementBuilder.addColumn(
    buildBlock: ColumnLayoutBuilder.() -> Unit = {},
    block: LayoutSubElementBuilder.() -> Unit = {}
){
    val builder = ColumnLayoutBuilder()
    builder.buildBlock()
    val subBuilder = LayoutSubElementBuilder(builder.layout)
    subBuilder.block()
    add(builder.build())
}

fun LayoutSubElementBuilder.column(
    buildBlock: ColumnLayoutBuilder.() -> Unit = {},
    block: LayoutSubElementBuilder.() -> Unit = {}
): ColumnLayout {
    val builder = ColumnLayoutBuilder()
    builder.buildBlock()
    val subBuilder = LayoutSubElementBuilder(builder.layout)
    subBuilder.block()
    return builder.build()
}