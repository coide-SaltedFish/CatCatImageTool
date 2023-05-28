package org.sereinfish.catcat.image.tool.core.element.layout

import org.jetbrains.skija.Canvas
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.draw.Drawer
import org.sereinfish.catcat.image.tool.core.element.AbstractElement
import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.core.measure.AlignmentLayout
import org.sereinfish.catcat.image.tool.utils.getSize
import org.sereinfish.catcat.image.tool.utils.toImage

abstract class AbstractLayout: AbstractElement(), Layout {
    override val subElementList: MutableList<Element> = ArrayList()

    // 默认子元素绘制器
    override var subElementDrawer: Drawer = object : Drawer{
        override fun draw(canvas: Canvas, context: DrawerContext) {
            for (i in subElementList.indices){
                val subElement = subElementList[i]
                val offset = getSubElementOffset(i)
                // 绘制
                val subCanvas = subElement.getCanvas()
                subElement.draw(subCanvas, DrawerContext(subElement, this@AbstractLayout, canvas))
                // 大小检查
                val subSize = subElement.bitmap!!.getSize()
                if (subSize.width == 0 || subSize.height == 0){
                    println("子元素 ${subElement::class.java.name} 的大小不合法: $subSize")
                }
                // 添加到画布
                canvas.drawImage(subElement.bitmap!!.toImage(), offset.x, offset.y)
            }
        }
    }

    override fun draw(canvas: Canvas, context: DrawerContext) {

        // 背景
        backgroundDrawer.draw(canvas, context)
        // 控件
        drawChain.draw(canvas, context)
        // 子元素
        subElementDrawer.draw(canvas, context)
        // 前景
        foregroundDrawer.draw(canvas, context)
    }
}