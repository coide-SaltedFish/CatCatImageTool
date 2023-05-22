package org.sereinfish.catcat.image.tool.builder

import org.jetbrains.skija.Image
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.layout.Layout
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.layout.ColumnLayout
import org.sereinfish.catcat.image.tool.utils.toImage

class ImageBuilder(
    val layout: Layout = ColumnLayout()
): ElementBuilder(layout) {
    var alignment: Alignment
        get() = layout.alignment
        set(value) { layout.alignment = value }

    fun build(): Image{
        layout.draw(layout.getCanvas(), DrawerContext(layout))
        return layout.bitmap?.toImage() ?: throw Exception("图片绘制异常")
    }
}

fun buildImage(
    layout: Layout = ColumnLayout(),
    buildBlock: ImageBuilder.() -> Unit = {},
    block: LayoutSubElementBuilder.() -> Unit
): Image{
    val builder = ImageBuilder(layout)
    builder.buildBlock()

    val subBuilder = LayoutSubElementBuilder(builder.layout)
    subBuilder.block()
    return builder.build()
}