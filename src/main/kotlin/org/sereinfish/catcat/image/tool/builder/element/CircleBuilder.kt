package org.sereinfish.catcat.image.tool.builder.element

import org.jetbrains.skija.Paint
import org.sereinfish.catcat.image.tool.builder.ElementBuilder
import org.sereinfish.catcat.image.tool.builder.LayoutSubElementBuilder
import org.sereinfish.catcat.image.tool.core.shadow.ShadowInfo
import org.sereinfish.catcat.image.tool.element.CircleElement

class CircleBuilder(
    val radius: Int,
    val paint: Paint,
    val circleShadow: ShadowInfo? = null,
    override val element: CircleElement = CircleElement(radius, paint, circleShadow)
): ElementBuilder(element) {

    fun build() = element
}

fun LayoutSubElementBuilder.addCircle(
    radius: Int,
    paint: Paint,
    circleShadow: ShadowInfo? = null,
    block: CircleBuilder.() -> Unit = {}
){
    add(circle(radius, paint, circleShadow, block))
}

fun LayoutSubElementBuilder.circle(
    radius: Int,
    paint: Paint,
    circleShadow: ShadowInfo? = null,
    block: CircleBuilder.() -> Unit = {}
): CircleElement {
    val builder = CircleBuilder(radius, paint, circleShadow)
    builder.block()
    return builder.build()
}