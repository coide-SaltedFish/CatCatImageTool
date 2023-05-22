package org.sereinfish.catcat.image.tool.builder.drawer

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.builder.ElementBuilder
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.draw.Drawer
import org.sereinfish.catcat.image.tool.utils.DrawerLevel
import org.sereinfish.catcat.image.tool.utils.paint

class FrameStrokeDrawer(
    val color: Int,
    val width: Int = 1,
): Drawer {
    override fun draw(canvas: Canvas, context: DrawerContext) {
        canvas.restore()
        canvas.drawRect(
            Rect.makeWH(
                context.element.getLayoutSize().width.toFloat(),
                context.element.getLayoutSize().height.toFloat()
            ), paint {
                color = this@FrameStrokeDrawer.color
                mode = PaintMode.STROKE
                strokeWidth = width.toFloat()
            })

        PaddingDrawer().draw(canvas, context)
    }
}

fun ElementBuilder.frameStroke(
    color: Int,
    width: Int = 1,
    level: Int = DrawerLevel.LOW
){
    addDrawer(FrameStrokeDrawer(color, width), level)
}