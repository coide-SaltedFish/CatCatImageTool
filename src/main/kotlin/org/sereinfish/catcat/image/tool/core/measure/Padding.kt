package org.sereinfish.catcat.image.tool.core.measure

import org.sereinfish.catcat.image.tool.core.measure.size.IntSize

/**
 * 内边距
 */
data class Padding(
    var left: Int = 0,
    var top: Int = 0,
    var right: Int = 0,
    var bottom: Int = 0,
){
    constructor(value: Int): this(value, value, value, value)


    fun getSize() =
        IntSize().apply {
            width = left + right
            height = top + bottom
        }
}
