package org.sereinfish.catcat.image.tool.builder.drawer

import org.jetbrains.skija.Canvas
import org.jetbrains.skija.RRect
import org.sereinfish.catcat.image.tool.builder.ElementBuilder
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.draw.Drawer
import org.sereinfish.catcat.image.tool.utils.DrawerLevel

/**
 * 圆角矩形
 */
class RoundRectDrawer(
    val tlRad: Float,
    val trRad: Float,
    val brRad: Float,
    val blRad: Float
): Drawer {
    constructor(radius: Float): this(radius, radius, radius, radius)

    override fun draw(canvas: Canvas, context: DrawerContext) {
        canvas.save()
        val size = context.element.getSize()
        val padding = context.element.padding

        canvas.clipRRect(RRect(padding.left.toFloat(), padding.top.toFloat(),
            size.width.toFloat() + tlRad, size.height.toFloat() + brRad, floatArrayOf(
            tlRad, trRad, brRad, blRad
        )))
    }
}

fun ElementBuilder.setRoundRect(
    tlRad: Float,
    trRad: Float,
    brRad: Float,
    blRad: Float,
    level: Int = DrawerLevel.NORMAL
){
    addDrawer(RoundRectDrawer(tlRad, trRad, brRad, blRad), level)
}

fun ElementBuilder.setRoundRect(radius: Float, level: Int = DrawerLevel.NORMAL){
    setRoundRect(radius, radius, radius, radius, level)
}