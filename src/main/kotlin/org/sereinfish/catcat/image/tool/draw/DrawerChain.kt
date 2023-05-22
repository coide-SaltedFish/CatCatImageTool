package org.sereinfish.catcat.image.tool.draw

import org.jetbrains.skija.Canvas
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.utils.DrawerLevel
import org.sereinfish.catcat.image.tool.utils.SortedList

/**
 * 绘制链
 */
class DrawerChain(
    val list: SortedList<Drawer> = SortedList()
) {
    fun add(drawer: Drawer, level: Int = DrawerLevel.NORMAL){
        list.add(drawer, level)
    }

    fun draw(canvas: Canvas, context: DrawerContext){
        list.forEach {
            it.draw(canvas, context)
        }
    }
}