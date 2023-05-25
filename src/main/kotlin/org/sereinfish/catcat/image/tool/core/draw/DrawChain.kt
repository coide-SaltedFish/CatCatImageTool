package org.sereinfish.catcat.image.tool.core.draw

import org.jetbrains.skija.Canvas
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.utils.SortedList

class DrawChain {
    val list = SortedList<Drawer>()

    fun add(drawer: Drawer, level: Int = 0){
        list.add(drawer, level)
    }

    fun draw(canvas: Canvas, context: DrawerContext){
        list.forEach {
            it.draw(canvas, context)
        }
    }
}