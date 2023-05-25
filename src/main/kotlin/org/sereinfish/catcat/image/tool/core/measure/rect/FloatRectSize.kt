package org.sereinfish.catcat.image.tool.core.measure.rect

import org.sereinfish.catcat.image.tool.core.measure.size.FloatSize

data class FloatRectSize(
    override var left: Float = 0f,
    override var top: Float = 0f,
    override var right: Float = 0f,
    override var bottom: Float = 0f
): RectSize<Float>{
    constructor(value: Float): this(value, value, value, value)

    fun add(rectSize: FloatRectSize){
        left += rectSize.left
        top += rectSize.top
        right += rectSize.right
        bottom += rectSize.bottom
    }

    fun getSize(): FloatSize {
        return FloatSize(left + right, top + bottom)
    }
}
