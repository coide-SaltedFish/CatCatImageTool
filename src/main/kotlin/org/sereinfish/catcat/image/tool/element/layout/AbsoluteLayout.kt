package org.sereinfish.catcat.image.tool.element.layout

import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.core.element.layout.AbstractLayout
import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSize
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize

class AbsoluteLayout: AbstractLayout() {
    private val subElementOffsetMap = HashMap<Int, FloatOffset>()

    override fun getAdaptiveSize(): IntSize {
        return IntSize().apply {
            subElementList.forEach {
                if (it.elementSize.widthSize.mode != ElementSize.SizeMode.MAX){
                    width = maxOf(width, it.elementSize.getWidth(it) + it.padding.getSize().width)
                }
                if (it.elementSize.heightSize.mode != ElementSize.SizeMode.MAX){
                    height = maxOf(height, it.elementSize.getHeight(it) + it.padding.getSize().height)
                }
            }
        }
    }

    override fun getSubElementMaxSize(index: Int, mode: ElementSize.ValueMode): Int {
        return when(mode){
            ElementSize.ValueMode.WIDTH -> {
                elementSize.getWidth(this)
            }
            ElementSize.ValueMode.HEIGHT -> {
                elementSize.getHeight(this)
            }
        }
    }

    override fun getSubElementOffset(index: Int): FloatOffset {
        return subElementOffsetMap[index] ?: FloatOffset()
    }

    fun add(pos: FloatOffset, element: Element){
        subElementOffsetMap[subElementList.size] = pos
        add(element)
    }

    infix fun FloatOffset.put(element: Element){
        add(this, element)
    }

    fun pos(x: Int, y: Int) = FloatOffset(x.toFloat(), y.toFloat())
}