package org.sereinfish.catcat.image.tool.builder.layout

import org.sereinfish.catcat.image.tool.builder.ElementBuilder
import org.sereinfish.catcat.image.tool.builder.LayoutSubElementBuilder
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.layout.RowLayout

class RowLayoutBuilder(
    val layout: RowLayout = RowLayout(),
): ElementBuilder(layout) {
    var alignment: Alignment
        get() = layout.alignment
        set(value) { layout.alignment = value }

    fun build() = layout
}

fun rowLayoutBuilder(
    buildBlock: RowLayoutBuilder.() -> Unit = {},
    block: LayoutSubElementBuilder.() -> Unit = {}
): RowLayout {
    val builder = RowLayoutBuilder()
    builder.buildBlock()
    val subBuilder = LayoutSubElementBuilder(builder.layout)
    subBuilder.block()
    return builder.build()
}

fun LayoutSubElementBuilder.addRow(
    buildBlock: RowLayoutBuilder.() -> Unit = {},
    block: LayoutSubElementBuilder.() -> Unit
){
    val builder = RowLayoutBuilder()
    builder.buildBlock()
    val subBuilder = LayoutSubElementBuilder(builder.layout)
    subBuilder.block()
    add(builder.build())
}

fun LayoutSubElementBuilder.row(
    buildBlock: RowLayoutBuilder.() -> Unit = {},
    block: LayoutSubElementBuilder.() -> Unit
): RowLayout{
    val builder = RowLayoutBuilder()
    builder.buildBlock()
    val subBuilder = LayoutSubElementBuilder(builder.layout)
    subBuilder.block()
    return builder.build()
}