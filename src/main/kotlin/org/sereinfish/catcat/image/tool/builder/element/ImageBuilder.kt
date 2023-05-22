package org.sereinfish.catcat.image.tool.builder.element

import org.jetbrains.skija.Image
import org.jetbrains.skija.Paint
import org.sereinfish.catcat.image.tool.builder.ElementBuilder
import org.sereinfish.catcat.image.tool.builder.LayoutSubElementBuilder
import org.sereinfish.catcat.image.tool.element.CropImageMode
import org.sereinfish.catcat.image.tool.element.ImageElement

class ImageBuilder(
    var image: Image,
    val imageElement: ImageElement = ImageElement(image)
): ElementBuilder(imageElement) {
    var cropImageMode: CropImageMode
        get() = imageElement.cropImageMode
        set(value) { imageElement.cropImageMode = value }

    var paint: Paint
        get() = imageElement.paint
        set(value) { imageElement.paint = value }

    fun build(): ImageElement{
        return imageElement
    }
}

fun LayoutSubElementBuilder.image(
    image: Image,
    block: ImageBuilder.() -> Unit = {}
): ImageElement{
    val builder = ImageBuilder(image)
    builder.block()
    return builder.build()
}

fun LayoutSubElementBuilder.addImage(
    image: Image,
    block: ImageBuilder.() -> Unit = {}
){
    val builder = ImageBuilder(image)
    builder.block()
    add(builder.build())
}