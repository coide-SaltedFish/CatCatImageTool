package org.sereinfish.catcat.image.tool.builder.element

import org.jetbrains.skija.Image
import org.sereinfish.catcat.image.tool.core.element.layout.Layout
import org.sereinfish.catcat.image.tool.element.ImageElement

fun buildImage(image: Image, block: ImageElement.() -> Unit = {}): ImageElement{
    val element = ImageElement(image)
    element.block()
    return element
}

fun Layout.image(image: Image, block: ImageElement.() -> Unit = {}){
    add(buildImage(image, block))
}