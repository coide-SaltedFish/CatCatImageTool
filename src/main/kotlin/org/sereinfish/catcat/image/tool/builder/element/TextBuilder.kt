package org.sereinfish.catcat.image.tool.builder.element

import org.jetbrains.skija.Font
import org.sereinfish.catcat.image.tool.builder.ElementBuilder
import org.sereinfish.catcat.image.tool.builder.LayoutSubElementBuilder
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.element.TextElement

class TextBuilder(
    text: String,
    font: Font,
    val textElement: TextElement = TextElement(text, font)
): ElementBuilder(textElement) {

    var text: String
        get() = textElement.text
        set(value) { textElement.text = value }

    var font: Font
        get() = textElement.font
        set(value) { textElement.font = value }

    var alignment: Alignment
        get() = textElement.alignment
        set(value) { textElement.alignment = value }

    fun build() = textElement
}

fun LayoutSubElementBuilder.addText(
    text: String,
    font: Font,
    block: TextBuilder.() -> Unit = {}
){
    val builder = TextBuilder(text, font)
    builder.block()
    add(builder.build())
}

fun LayoutSubElementBuilder.text(
    text: String,
    font: Font,
    block: TextBuilder.() -> Unit = {}
): TextElement {
    val builder = TextBuilder(text, font)
    builder.block()
    return builder.build()
}