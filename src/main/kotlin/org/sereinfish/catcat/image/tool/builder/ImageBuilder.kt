package org.sereinfish.catcat.image.tool.builder

import org.jetbrains.skija.Image
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.element.layout.RowLayout
import org.sereinfish.catcat.image.tool.utils.toImage

class ImageBuilder(
    var layout: RowLayout = RowLayout()
) {

    fun build(): Image{
        layout.draw(layout.getCanvas(), DrawerContext(layout))
        return layout.bitmap!!.toImage()
    }
}

/**
 * 构建图像
 */
fun buildImage(block: RowLayout.() -> Unit): Image{
    val builder = ImageBuilder()
    block.invoke(builder.layout)
    return builder.build()
}