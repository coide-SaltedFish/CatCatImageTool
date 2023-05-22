package org.sereinfish.catcat.image.tool.builder.layout

import org.sereinfish.catcat.image.tool.builder.ElementBuilder
import org.sereinfish.catcat.image.tool.builder.LayoutSubElementBuilder
import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset
import org.sereinfish.catcat.image.tool.layout.AbsoluteLayout

class AbsoluteLayoutBuilder(
    val layout: AbsoluteLayout = AbsoluteLayout(),
): ElementBuilder(layout) {

    fun build() = layout
}

class AbsoluteLayoutSubElementBuilder(
    private val absoluteLayout: AbsoluteLayout
): LayoutSubElementBuilder(absoluteLayout){

    infix fun FloatOffset.put(element: Element){
        absoluteLayout.add(this, element)
    }

    fun floatOffset(x: Float, y: Float) = FloatOffset(x, y)
}

fun absoluteLayoutBuilder(
    buildBlock: AbsoluteLayoutBuilder.() -> Unit = {},
    block: AbsoluteLayoutSubElementBuilder.() -> Unit
): AbsoluteLayout {
    val builder = AbsoluteLayoutBuilder()
    builder.buildBlock()
    val subBuilder = AbsoluteLayoutSubElementBuilder(builder.layout)
    subBuilder.block()
    return builder.build()
}

fun LayoutSubElementBuilder.addAbs(
    buildBlock: AbsoluteLayoutBuilder.() -> Unit = {},
    block: AbsoluteLayoutSubElementBuilder.() -> Unit = {}
){
    add(absoluteLayoutBuilder(buildBlock, block))
}

fun LayoutSubElementBuilder.abs(
    buildBlock: AbsoluteLayoutBuilder.() -> Unit = {},
    block: AbsoluteLayoutSubElementBuilder.() -> Unit = {}
) = absoluteLayoutBuilder(buildBlock, block)