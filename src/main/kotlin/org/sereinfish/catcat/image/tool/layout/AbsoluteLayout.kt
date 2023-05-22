package org.sereinfish.catcat.image.tool.layout

import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset
import org.sereinfish.catcat.image.tool.core.measure.offset.IntOffset
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSizeMode
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import kotlin.math.roundToInt

/**
 * 绝对布局
 */
class AbsoluteLayout: AbstractLayout() {
    override var alignment: Alignment = Alignment.LEFT

    // 坐标表
    val offsetMap = HashMap<Int, FloatOffset>()

    fun add(offset: FloatOffset, element: Element){
        offsetMap[subElements.size] = offset
        add(element)
    }

    override fun getSubElementOffset(index: Int): FloatOffset {
        return offsetMap[index] ?: FloatOffset()
    }

    override fun getSize(): IntSize {
        return if (elementSize.mode == ElementSizeMode.DEFINED_MODE){
            IntSize().apply {
                subElements.forEachIndexed { index, element ->
                    val offset = getSubElementOffset(index)
                    val size = element.getLayoutSize()

                    width = maxOf(width, (offset.x + size.width).roundToInt())
                    height = maxOf(height, (offset.y + size.height).roundToInt())
                }
            }
        }else elementSize.size
    }
}