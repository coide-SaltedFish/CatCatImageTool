package org.sereinfish.catcat.image.tool.layout

import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.core.measure.AlignmentLayout
import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset
import org.sereinfish.catcat.image.tool.core.measure.offset.IntOffset
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSizeMode
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize

/**
 * 列
 */
class ColumnLayout(
    override var alignment: Alignment = Alignment.LEFT
): AbstractLayout() {

    override fun getSubElementOffset(index: Int): FloatOffset {
        val offset = FloatOffset()
        for (i in 0 until index){
            val element = subElements.getOrNull(i) ?: return FloatOffset()
            offset.y = offset.y + element.getLayoutSize().height
        }
        return getAlignmentOffset(offset, index, alignment)
    }

    override fun getAlignmentOffset(offset: FloatOffset, index: Int, alignment: Alignment): FloatOffset {
        when(alignment){
            Alignment.LEFT -> offset.x = 0f
            Alignment.RIGHT -> offset.x = (getSize().width - subElements[index].getLayoutSize().width).toFloat()
            Alignment.CENTER_HORIZONTAL -> {
                offset.x = (getSize().width - subElements[index].getLayoutSize().width) / 2f
            }
            else -> {}
        }
        return offset
    }

    override fun getSize(): IntSize {
        return if (elementSize.mode == ElementSizeMode.DEFINED_MODE){
            IntSize().apply {
                subElements.forEach {
                    val elementSize = it.getLayoutSize()
                    width = maxOf(width, elementSize.width)
                    height += elementSize.height
                }
            }
        } else elementSize.getSize(this)
    }
}