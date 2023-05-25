package org.sereinfish.catcat.image.tool.core.measure.size

import org.jetbrains.skija.Rect

data class FloatSize(
    override var width: Float = 0f,
    override var height: Float = 0f
): Size<Float>{
    fun add(size: FloatSize){
        width += size.width
        height += size.height
    }

    fun getRect(left: Float = 0f, top: Float = 0f): Rect {
        return Rect.makeXYWH(left, top, width, height)
    }

    override fun toString(): String {
        return "FloatSize(width=$width, height=$height)"
    }
}
