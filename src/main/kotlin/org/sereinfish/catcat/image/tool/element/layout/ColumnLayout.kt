package org.sereinfish.catcat.image.tool.element.layout

import org.sereinfish.catcat.image.tool.core.element.layout.AbstractLayout
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.core.measure.AlignmentLayout
import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSize
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize

class ColumnLayout: AbstractLayout(), AlignmentLayout {
    override var alignment: Alignment = Alignment.LEFT

    override fun getAdaptiveSize(): IntSize {
        return IntSize().apply {
            subElementList.forEach {
                if (it.elementSize.widthSize.mode != ElementSize.SizeMode.MAX){
                    width = maxOf(width, it.elementSize.getWidth(it) + it.padding.getSize().width)
                }
                if (it.elementSize.heightSize.mode != ElementSize.SizeMode.MAX){
                    height += it.elementSize.getHeight(it) + it.padding.getSize().height
                }

            }
        }
    }

    override fun getSubElementMaxSize(index: Int, mode: ElementSize.ValueMode): Int {
        return when(mode){
            ElementSize.ValueMode.WIDTH -> {
                elementSize.getWidth(this) - padding.getSize().width
            }
            ElementSize.ValueMode.HEIGHT -> {
                elementSize.getHeight(this) - subElementList.subList(0, index)
                    .sumOf { it.getLayoutSize().height } - padding.getSize().height
            }
        }
    }

    override fun getSubElementOffset(index: Int): FloatOffset {
        return getAlignmentOffset(index)
    }

    override fun getAlignmentOffset(index: Int): FloatOffset {
        val subElement = subElementList[index]
        val size = getSize().apply {
            width -= padding.getSize().width
            height -= padding.getSize().height
        }

        // 子大小
        val subSize = subElement.getLayoutSize().apply {
            height = subElementList.sumOf { it.getLayoutSize().height }
        }

        val baseY = subElementList.subList(0, index).sumOf { it.getLayoutSize().height }.toFloat() + padding.top
        return getAlignmentOffset(size, subSize).apply {
            y += baseY
            x += padding.left
        }
    }
}