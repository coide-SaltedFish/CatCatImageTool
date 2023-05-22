package org.sereinfish.catcat.image.tool.element

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.builder.drawer.ElementShadowDrawer
import org.sereinfish.catcat.image.tool.builder.drawer.PaddingDrawer
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.core.measure.Padding
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSize
import org.sereinfish.catcat.image.tool.core.shadow.ShadowInfo
import org.sereinfish.catcat.image.tool.draw.Drawer
import org.sereinfish.catcat.image.tool.draw.DrawerChain

abstract class AbstractElement: Element {
    override var bitmap: Bitmap? = null
    override var parent: Element? = null

    override var padding: Padding = Padding() // 内边距
    override var backgroundColor: Int? = null // 背景颜色

    override var elementSize: ElementSize = ElementSize()
    override var shadowInfo: ShadowInfo? = null
    final override val drawerChain: DrawerChain = DrawerChain()

    override val subElements: MutableList<Element> = ArrayList()

    init {
        // 阴影
        drawerChain.add(ElementShadowDrawer())
        // 内边距
        drawerChain.add(PaddingDrawer())
        // 背景颜色
        drawerChain.add(object : Drawer{
            override fun draw(canvas: Canvas, context: DrawerContext) {
                backgroundColor?.let {
                    val size = getLayoutSize()
                    Paint().use { paint ->
                        paint.color = it
                        canvas.drawRect(Rect.makeWH(size.width.toFloat(), size.height.toFloat()), paint)
                    }
                }
            }
        })
    }
}