package org.sereinfish.catcat.image.tool.core.measure.size

data class IntSize(
    override var width: Int = 0,
    override var height: Int = 0
) : Size<Int> {
    constructor(size: Int): this(size, size)

    override fun Size<Int>.plus(other: Size<Int>): Size<Int> {
        this.width += other.width
        this.height += other.height

        return this
    }

    fun add(size: IntSize){
        this.width += size.width
        this.height += size.height
    }

    override fun toString(): String{
        return "[width: $width, height: $height]"
    }
}