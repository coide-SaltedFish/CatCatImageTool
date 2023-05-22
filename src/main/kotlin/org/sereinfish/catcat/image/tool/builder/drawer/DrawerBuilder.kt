package org.sereinfish.catcat.image.tool.builder.drawer

import org.jetbrains.skija.Canvas
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.draw.Drawer

inline fun buildDrawer(crossinline drawer: (Canvas, DrawerContext) -> Unit): Drawer{
    return object : Drawer {
        override fun draw(canvas: Canvas, context: DrawerContext) {
            drawer(canvas, context)
        }
    }
}