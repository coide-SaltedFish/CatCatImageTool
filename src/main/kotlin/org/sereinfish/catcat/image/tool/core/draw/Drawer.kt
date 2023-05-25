package org.sereinfish.catcat.image.tool.core.draw

import org.jetbrains.skija.Canvas
import org.sereinfish.catcat.image.tool.core.context.DrawerContext

/**
 * 绘制器
 */
interface Drawer {
    fun draw(canvas: Canvas, context: DrawerContext)
}