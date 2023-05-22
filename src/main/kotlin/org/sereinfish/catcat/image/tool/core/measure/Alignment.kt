package org.sereinfish.catcat.image.tool.core.measure

import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset
import org.sereinfish.catcat.image.tool.core.measure.offset.IntOffset

/**
 * 对齐
 */
interface AlignmentLayout{

    /**
     * 获取在指定对齐方式情况下的坐标
     */
    fun getAlignmentOffset(offset: FloatOffset, index: Int, alignment: Alignment): FloatOffset
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

    /**
     * 获取排列坐标
     */
    fun getOffset(offset: IntOffset, alignment: Alignment): IntOffset{
        return IntOffset()
    }
}