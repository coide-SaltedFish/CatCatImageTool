package org.sereinfish.catcat.image.tool.builder.element

import org.jetbrains.skija.Paint
import org.sereinfish.catcat.image.tool.builder.ElementBuilder
import org.sereinfish.catcat.image.tool.builder.LayoutSubElementBuilder
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.element.RectElement
import org.sereinfish.catcat.image.tool.utils.paint

class RectBuilder(
    val size: IntSize,
    val paint: Paint,
    override val element: RectElement = RectElement(size.width, size.height, paint)
): ElementBuilder(element) {

    fun build() = element
}

fun LayoutSubElementBuilder.addRect(
    size: IntSize,
    color: Int,
    block: RectBuilder.() -> Unit = {}
){
    add(rect(size, color, block))
}

fun LayoutSubElementBuilder.rect(
    size: IntSize,
    color: Int,
    block: RectBuilder.() -> Unit = {}
): RectElement {
    val builder = RectBuilder(size, paint {
        setColor(color)
    })
    builder.block()
    return builder.build()
}