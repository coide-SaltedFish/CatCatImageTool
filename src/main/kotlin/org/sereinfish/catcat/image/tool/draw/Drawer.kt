package org.sereinfish.catcat.image.tool.draw

import org.jetbrains.skija.Canvas
import org.sereinfish.catcat.image.tool.core.context.DrawerContext

/**
 * 一个绘制单元
 */
interface Drawer {
    /**
     * 进行一次绘制处理
     */
    fun draw(canvas: Canvas, context: DrawerContext)
}