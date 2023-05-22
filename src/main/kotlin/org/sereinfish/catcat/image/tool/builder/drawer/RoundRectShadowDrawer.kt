package org.sereinfish.catcat.image.tool.builder.drawer

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.builder.ElementBuilder
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.shadow.ShadowInfo
import org.sereinfish.catcat.image.tool.draw.Drawer
import org.sereinfish.catcat.image.tool.utils.DrawerLevel
import org.sereinfish.catcat.image.tool.utils.paint

class RoundRectShadowDrawer(
    val shadowInfo: ShadowInfo,
    val tlRad: Float,
    val trRad: Float,
    val brRad: Float,
    val blRad: Float
): Drawer {
    constructor(shadowInfo: ShadowInfo, radius: Float): this(shadowInfo, radius, radius, radius, radius)

    override fun draw(canvas: Canvas, context: DrawerContext) {
        val size = context.element.getSize()
        val padding = context.element.padding

        val rRect = RRect(padding.left.toFloat(), padding.top.toFloat(),
            size.width.toFloat() + tlRad, size.height.toFloat() + brRad, floatArrayOf(
            tlRad, trRad, brRad, blRad
        ))
        // 绘制阴影
        canvas.drawRRect(rRect, paint {
            imageFilter = shadowInfo.getDropShadowOnlyImageFilter()
        })
    }
}

fun ElementBuilder.setRoundRectShadow(
    tlRad: Float,
    trRad: Float,
    brRad: Float,
    blRad: Float,
    shadowInfo: ShadowInfo,
    level: Int = DrawerLevel.NORMAL
){
    addDrawer(RoundRectShadowDrawer(shadowInfo,tlRad, trRad, brRad, blRad), level)
}


fun ElementBuilder.setRoundRectShadow(
    radius: Float,
    shadowInfo: ShadowInfo,
    level: Int = DrawerLevel.NORMAL
){
    setRoundRectShadow(radius, radius, radius, radius, shadowInfo, level)
}