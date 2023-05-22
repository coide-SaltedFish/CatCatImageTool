package org.sereinfish.catcat.image.tool.core.measure.size

interface Size<T: Number> {
    var width: T
    var height: T

    operator fun Size<T>.plus(other: Size<T>): Size<T>
}