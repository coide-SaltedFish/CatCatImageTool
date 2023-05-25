package org.sereinfish.catcat.image.tool.core.measure

import org.jetbrains.skija.Color
import org.jetbrains.skija.ImageFilter

data class ShadowInfo(
    var dx: Int = 0,
    var dy: Int = 0,

    var sigmaX: Int = 0,
    var sigmaY: Int = 0,

    var color: Int = Color.makeRGB(97, 97, 97)
){
    constructor(
        offset: Int,
        sigma: Int,
        color: Int = Color.makeRGB(97, 97, 97)
    ): this(offset, offset, sigma, sigma, color)

    fun getDropShadowImageFilter() =
        ImageFilter.makeDropShadow(dx.toFloat(), dy.toFloat(), sigmaX.toFloat(), sigmaY.toFloat(), color)


    fun getDropShadowOnlyImageFilter() =
        ImageFilter.makeDropShadowOnly(dx.toFloat(), dy.toFloat(), sigmaX.toFloat(), sigmaY.toFloat(), color)
}
