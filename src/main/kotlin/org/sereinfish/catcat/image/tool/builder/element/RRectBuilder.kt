package org.sereinfish.catcat.image.tool.builder.element

import org.jetbrains.skija.Paint
import org.jetbrains.skija.paragraph.Shadow
import org.sereinfish.catcat.image.tool.builder.ElementBuilder
import org.sereinfish.catcat.image.tool.builder.LayoutSubElementBuilder
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.core.shadow.ShadowInfo
import org.sereinfish.catcat.image.tool.element.RRectElement
import org.sereinfish.catcat.image.tool.utils.paint

class RRectBuilder(
    val size: IntSize,
    val tlRad: Float,
    val trRad: Float,
    val brRad: Float,
    val blRad: Float,
    val paint: Paint,
    override val element: RRectElement = RRectElement(size.width, size.height, tlRad, trRad, brRad, blRad, null, paint)
): ElementBuilder(element) {
    var rRectShadow: ShadowInfo?
        get() = element.rRectShadowInfo
        set(value) { element.rRectShadowInfo = value }

    fun build() = element
}

fun LayoutSubElementBuilder.addRRect(
    size: IntSize,
    tlRad: Float,
    trRad: Float,
    brRad: Float,
    blRad: Float,
    color: Int,
    block: RRectBuilder.() -> Unit = {}
){
    add(rRect(size, tlRad, trRad, brRad, blRad, color, block))
}

fun LayoutSubElementBuilder.addRRect(
    size: IntSize,
    radius: Float,
    color: Int,
    block: RRectBuilder.() -> Unit = {}
){
    add(rRect(size, radius, color, block))
}

fun LayoutSubElementBuilder.rRect(
    size: IntSize,
    radius: Float,
    color: Int,
    block: RRectBuilder.() -> Unit = {}
): RRectElement = rRect(size, radius, radius, radius, radius, color, block)

fun LayoutSubElementBuilder.rRect(
    size: IntSize,
    tlRad: Float,
    trRad: Float,
    brRad: Float,
    blRad: Float,
    color: Int,
    block: RRectBuilder.() -> Unit = {}
): RRectElement {
    val builder = RRectBuilder(size, tlRad, trRad, brRad, blRad, paint {
        setColor(color)
    })
    builder.block()
    return builder.build()
}
