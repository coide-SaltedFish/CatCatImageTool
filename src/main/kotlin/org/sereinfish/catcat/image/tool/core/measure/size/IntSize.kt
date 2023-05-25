package org.sereinfish.catcat.image.tool.core.measure.size

import org.jetbrains.skija.Rect

data class IntSize(
    override var width: Int = 0,
    override var height: Int = 0
): Size<Int>{
    fun add(size: IntSize){
        width += size.width
        height += size.height
    }

    fun getRect(left: Int = 0, top: Int = 0): Rect {
        return Rect.makeXYWH(left.toFloat(), top.toFloat(), width.toFloat(), height.toFloat())
    }

    override fun toString(): String {
        return "IntSize(width=$width, height=$height)"
    }
}
