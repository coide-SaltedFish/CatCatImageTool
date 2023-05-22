package org.sereinfish.catcat.image.tool.layout

import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Rect
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.layout.Layout
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.core.measure.AlignmentLayout
import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset
import org.sereinfish.catcat.image.tool.core.measure.offset.IntOffset
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSizeMode
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.draw.Drawer
import org.sereinfish.catcat.image.tool.element.AbstractElement
import org.sereinfish.catcat.image.tool.utils.toImage

/**
 * 基本布局
 */
abstract class AbstractLayout: AbstractElement(), Layout, AlignmentLayout {

    init {
        // 添加子元素的绘制
        drawerChain.add(object : Drawer{
            override fun draw(canvas: Canvas, context: DrawerContext) {
                subElements.forEachIndexed { index, subElement ->
                    val offset = getSubElementOffset(index) // 获取子元素位置
                    val size = subElement.getLayoutSize() // 子元素大小

                    val subCanvas = subElement.getCanvas()
                    subElement.draw(subCanvas, DrawerContext(subElement, this@AbstractLayout, canvas)) // 子元素绘制

                    // 绘制到布局
                    canvas.save()
                    canvas.translate(offset.x, offset.y)
                    canvas.clipRect(Rect.makeWH(size.width.toFloat(), size.height.toFloat()))

                    subElement.bitmap?.let {
                        canvas.drawImage(it.toImage(), 0f, 0f)
                    } ?: println("子元素内容为 null")
                    canvas.restore()
                }
            }
        })
    }

    override fun getSize(): IntSize {
        return IntSize().apply {
            subElements.forEach { element ->
                if (element.elementSize.mode != ElementSizeMode.FILL_MODE){
                    val elementSize = element.getLayoutSize()
                    width += elementSize.width
                    height = maxOf(height, elementSize.height)
                }
            }
        }
    }

    override fun getAlignmentOffset(offset: FloatOffset, index: Int, alignment: Alignment): FloatOffset {
        return offset
    }
}