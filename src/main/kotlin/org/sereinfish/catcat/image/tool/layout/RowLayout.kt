package org.sereinfish.catcat.image.tool.layout

import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSizeMode
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize

/**
 * 行布局
 */
class RowLayout(
    override var alignment: Alignment = Alignment.TOP
): AbstractLayout() {
    override fun getSubElementOffset(index: Int): FloatOffset {
        val offset = FloatOffset()
        for (i in 0 until index){
            val element = subElements.getOrNull(i) ?: return FloatOffset()
            offset.x = offset.x + element.getLayoutSize().width
        }
        return getAlignmentOffset(offset, index, alignment)
    }

    override fun getAlignmentOffset(offset: FloatOffset, index: Int, alignment: Alignment): FloatOffset {
        when(alignment){
            Alignment.TOP -> offset.y = 0f
            Alignment.BOTTOM -> offset.y = (getSize().height - subElements[index].getLayoutSize().height).toFloat()
            Alignment.CENTER_VERTICAL -> {
                offset.y = (getSize().height - subElements[index].getLayoutSize().height) / 2f
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
                    height = maxOf(height, elementSize.height)
                    width += elementSize.width
                }
            }
        } else super.getSize()
    }
}