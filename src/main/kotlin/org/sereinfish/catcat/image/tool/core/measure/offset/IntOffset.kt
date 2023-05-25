package org.sereinfish.catcat.image.tool.core.measure.offset

import org.jetbrains.skija.Rect

data class IntOffset(
    override var x: Int = 0,
    override var y: Int = 0
): Offset<Int>{
    fun add(offset: IntOffset){
        x += offset.x
        y += offset.y
    }

    override fun toString(): String {
        return "IntOffset(x=$x, y=$y)"
    }
}
