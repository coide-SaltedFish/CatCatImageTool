package org.sereinfish.catcat.image.tool.builder.element

import org.jetbrains.skija.Font
import org.sereinfish.catcat.image.tool.core.element.layout.Layout
import org.sereinfish.catcat.image.tool.core.measure.ShadowInfo
import org.sereinfish.catcat.image.tool.element.TextElement

fun buildText(text: String, font: Font, block: TextElement.() -> Unit = {}): TextElement{
    val element = TextElement(text, font)
    element.block()
    return element
}

fun Layout.text(text: String, font: Font, block: TextElement.() -> Unit = {}){
    add(buildText(text, font, block))
}

/**
 * 为文本添加阴影
 */
fun TextElement.setTextShadow(shadowInfo: ShadowInfo){
    paint.apply {
        imageFilter = shadowInfo.getDropShadowImageFilter()
    }
}

/**
 * 设置文本颜色
 */
fun TextElement.setTextColor(color: Int){
    paint.apply {
        setColor(color)
    }
}