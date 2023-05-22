package org.sereinfish.catcat.image.tool.core.layout

import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset

interface Layout: Element {
    var alignment: Alignment

    /**
     * 获取子元素位置
     */
    fun getSubElementOffset(index: Int): FloatOffset
}