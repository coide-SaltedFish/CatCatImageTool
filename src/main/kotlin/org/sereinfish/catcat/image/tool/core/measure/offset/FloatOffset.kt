package org.sereinfish.catcat.image.tool.core.measure.offset

class FloatOffset(
    override var x: Float = 0f,
    override var y: Float = 0f
) : Offset<Float> {
    fun add(offset: FloatOffset){
        x += offset.x
        y += offset.y
    }

    override fun toString(): String {
        return "FloatOffset(x=$x, y=$y)"
    }
}