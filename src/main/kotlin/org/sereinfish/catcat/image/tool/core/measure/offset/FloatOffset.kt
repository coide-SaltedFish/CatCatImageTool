package org.sereinfish.catcat.image.tool.core.measure.offset

data class FloatOffset(
    override var x: Float = 0f,
    override var y: Float = 0f
) : Offset<Float>