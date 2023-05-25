package org.sereinfish.catcat.image.tool.core.measure

import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset
import org.sereinfish.catcat.image.tool.core.measure.offset.IntOffset
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize

/**
 * 对齐
 */
interface AlignmentLayout{
    var alignment: Alignment

    fun getAlignmentOffset(index: Int): FloatOffset

    /**
     * 获取在指定对齐方式情况下的坐标
     * 传入容器大小、元素大小
     * 输出元素在容器的坐标
     */
    fun getAlignmentOffset(containerSize: IntSize, size: IntSize): FloatOffset{
        return when(alignment){
            Alignment.TOP, Alignment.LEFT -> FloatOffset()
            Alignment.BOTTOM -> FloatOffset().apply {
                y = (containerSize.height - size.height).toFloat()
            }
            Alignment.RIGHT -> FloatOffset().apply {
                x = (containerSize.width - size.width).toFloat()
            }
            Alignment.CENTER_VERTICAL -> FloatOffset(y = (containerSize.height - size.height) / 2f)
            Alignment.CENTER_HORIZONTAL -> FloatOffset(x = (containerSize.width - size.width) / 2f)
            Alignment.CENTER -> FloatOffset().apply {
                y = (containerSize.height - size.height) / 2f
                x = (containerSize.width - size.width) / 2f
            }
        }
    }
}

enum class Alignment {
    TOP, // 顶部对齐
    BOTTOM, // 底部对齐
    LEFT, // 左侧对齐
    RIGHT, // 右侧对齐
    CENTER, // 整体居中
    CENTER_HORIZONTAL, // 水平居中
    CENTER_VERTICAL, // 垂直居中
    ;
}