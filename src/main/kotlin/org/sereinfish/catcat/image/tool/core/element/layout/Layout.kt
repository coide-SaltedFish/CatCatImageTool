package org.sereinfish.catcat.image.tool.core.element.layout

import org.sereinfish.catcat.image.tool.core.draw.Drawer
import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSize

interface Layout: Element {
    val subElementList: MutableList<Element> // 子元素链
    var subElementDrawer: Drawer // 子元素绘制

    /**
     * 添加子元素
     */
    fun add(element: Element){
        element.parent = this
        element.parentIndex = subElementList.size
        subElementList.add(element)
    }

    /**
     * 获取子元素最大化时的大小
     */
    fun getSubElementMaxSize(index: Int, mode: ElementSize.ValueMode): Int

    /**
     * 获取子元素位置
     */
    fun getSubElementOffset(index: Int): FloatOffset
}