package org.sereinfish.catcat.image.tool.builder.drawer

import org.jetbrains.skija.Canvas
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.draw.Drawer

fun buildDrawer(block: (Canvas, context: DrawerContext) -> Unit): Drawer =
    object : Drawer{
        override fun draw(canvas: Canvas, context: DrawerContext) {
            block(canvas, context)
        }
    }