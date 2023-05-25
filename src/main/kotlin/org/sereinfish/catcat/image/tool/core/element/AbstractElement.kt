package org.sereinfish.catcat.image.tool.core.element

import org.jetbrains.skija.Bitmap
import org.jetbrains.skija.Canvas
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.draw.DrawChain
import org.sereinfish.catcat.image.tool.core.element.layout.Layout
import org.sereinfish.catcat.image.tool.core.measure.rect.IntRectSize
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSize

abstract class AbstractElement: Element {
    override var parentIndex: Int? = null
    override var bitmap: Bitmap? = null
    override var parent: Layout? = null
    override var padding: IntRectSize = IntRectSize()
    override var margin: IntRectSize = IntRectSize()
    override var elementSize: ElementSize = ElementSize()

    override var backgroundDrawer: DrawChain = DrawChain()
    override var drawChain: DrawChain = DrawChain()
    override var foregroundDrawer: DrawChain = DrawChain()

    override fun draw(canvas: Canvas, context: DrawerContext) {
        // 背景
        backgroundDrawer.draw(canvas, context)
        // 控件
        drawChain.draw(canvas, context)
        // 前景
        foregroundDrawer.draw(canvas, context)
    }
}