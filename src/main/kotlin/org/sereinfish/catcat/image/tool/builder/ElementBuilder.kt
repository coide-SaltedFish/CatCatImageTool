package org.sereinfish.catcat.image.tool.builder

import org.jetbrains.skija.Canvas
import org.sereinfish.catcat.image.tool.builder.drawer.buildDrawer
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.core.measure.Padding
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSizeMode
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.core.shadow.ShadowInfo
import org.sereinfish.catcat.image.tool.draw.Drawer
import org.sereinfish.catcat.image.tool.utils.DrawerLevel

open class ElementBuilder(
    open val element: Element
) {
    var padding: Padding
        get() = element.padding
        set(value) { element.padding = value }

    var shadowInfo: ShadowInfo
        get() = element.shadowInfo
        set(value) { element.shadowInfo = value }

    var backgroundColor: Int?
        get() = element.backgroundColor
        set(value) { element.backgroundColor = value }

    fun setSize(width: Int, height: Int){
        element.elementSize.mode = ElementSizeMode.SET_MODE
        element.elementSize.size = IntSize(width, height)
    }

    fun setSize(size: Int){
        element.elementSize.mode = ElementSizeMode.SET_MODE
        element.elementSize.size = IntSize(size, size)
    }

    fun setSizeMode(mode: ElementSizeMode){
        element.elementSize.mode = mode
    }

    fun addDrawer(level: Int = DrawerLevel.NORMAL, drawer: (Canvas, DrawerContext) -> Unit){
        element.drawerChain.add(object : Drawer{
            override fun draw(canvas: Canvas, context: DrawerContext) {
                drawer(canvas, context)
            }
        })
    }

    fun restore(level: Int = DrawerLevel.NORMAL){
        element.drawerChain.add(buildDrawer { canvas, _ ->
            canvas.restore()
        }, level)
    }

    fun save(level: Int = DrawerLevel.NORMAL){
        element.drawerChain.add(buildDrawer { canvas, _ ->
            canvas.save()
        }, level)
    }

    fun addDrawer(drawer: Drawer, level: Int = DrawerLevel.NORMAL){
        element.drawerChain.add(drawer, level)
    }
}