package org.sereinfish.catcat.image.tool.builder.drawer.background

import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Image
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.draw.Drawer
import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.core.measure.CropImageMode
import org.sereinfish.catcat.image.tool.utils.paint

class BackgroundImageDrawer(
    val image: Image,
    val cropImageMode: CropImageMode = CropImageMode.Crop
): Drawer {
    override fun draw(canvas: Canvas, context: DrawerContext) {
        val size = context.element.getLayoutSize()
        canvas.drawImage(cropImageMode.getImage(image, size), 0f, 0f, paint())
    }
}

fun Element.setBackgroundImage(image: Image, cropImageMode: CropImageMode = CropImageMode.Crop){
    backgroundDrawer.add(BackgroundImageDrawer(image, cropImageMode))
}