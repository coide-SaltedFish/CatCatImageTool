package org.sereinfish.catcat.image.tool.builder.drawer

import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Rect
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.draw.Drawer

class PaddingDrawer: Drawer {
    override fun draw(canvas: Canvas, context: DrawerContext) {
        canvas.save()
        canvas.translate(context.element.padding.top.toFloat(), context.element.padding.left.toFloat())
        val size = context.element.getSize()
        canvas.clipRect(Rect.makeWH(size.width.toFloat(), size.height.toFloat()))
    }
}