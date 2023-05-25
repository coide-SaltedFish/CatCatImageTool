package org.sereinfish.catcat.image.tool.core.measure.rect

import org.sereinfish.catcat.image.tool.core.measure.size.IntSize

data class IntRectSize(
    override var left: Int = 0,
    override var top: Int = 0,
    override var right: Int = 0,
    override var bottom: Int = 0
) : RectSize<Int> {
    constructor(value: Int): this(value, value, value, value)

    fun add(rectSize: IntRectSize) {
        left += rectSize.left
        top += rectSize.top
        right += rectSize.right
        bottom += rectSize.bottom
    }

    fun getSize(): IntSize{
        return IntSize(left + right, top + bottom)
    }
}