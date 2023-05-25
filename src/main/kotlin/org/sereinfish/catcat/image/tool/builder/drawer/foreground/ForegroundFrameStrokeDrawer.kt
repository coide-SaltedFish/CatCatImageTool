package org.sereinfish.catcat.image.tool.builder.drawer.foreground

import org.jetbrains.skija.Canvas
import org.jetbrains.skija.PaintMode
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.draw.Drawer
import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.utils.paint

/**
 * 控件描边线
 */
class ForegroundFrameStrokeDrawer(
    val color: Int,
    val strokeWidth: Float = 1f
): Drawer {
    override fun draw(canvas: Canvas, context: DrawerContext) {
        val size = context.element.getLayoutSize()
        canvas.drawRect(size.getRect(), paint {
            mode = PaintMode.STROKE
            color = this@ForegroundFrameStrokeDrawer.color
            strokeWidth = this@ForegroundFrameStrokeDrawer.strokeWidth
        })
    }
}

fun Element.setForegroundFrameStroke(color: Int, strokeWidth: Float = 1f){
    foregroundDrawer.add(ForegroundFrameStrokeDrawer(color, strokeWidth))
}