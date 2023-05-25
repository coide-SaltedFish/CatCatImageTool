package org.sereinfish.catcat.image.tool.builder.drawer.background

import org.jetbrains.skija.Canvas
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.draw.Drawer
import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.utils.paint

class BackgroundColorDrawer(
    val color: Int
): Drawer {
    override fun draw(canvas: Canvas, context: DrawerContext) {
        canvas.drawRect(context.element.getLayoutSize().getRect(), paint {
            color = this@BackgroundColorDrawer.color
        })
    }
}

fun Element.setBackgroundColor(color: Int){
    backgroundDrawer.add(BackgroundColorDrawer(color))
}