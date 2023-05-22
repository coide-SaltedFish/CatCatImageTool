package org.sereinfish.catcat.image.tool.element

import org.jetbrains.skija.Paint
import org.jetbrains.skija.RRect
import org.sereinfish.catcat.image.tool.builder.drawer.PaddingDrawer
import org.sereinfish.catcat.image.tool.builder.drawer.buildDrawer
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSizeMode
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.core.shadow.ShadowInfo
import org.sereinfish.catcat.image.tool.utils.paint

class RRectElement(
    var width: Int,
    var height: Int,
    var tlRad: Float,
    var trRad: Float,
    var brRad: Float,
    var blRad: Float,
    var rRectShadowInfo: ShadowInfo? = null,
    var paint: Paint = paint(),
): AbstractElement() {
    constructor(
        width: Int,
        height: Int,
        radius: Float,
        rRectShadowInfo: ShadowInfo? = null,
        paint: Paint = paint()
    ): this(width, height, radius, radius, radius, radius, rRectShadowInfo, paint)

    init {
        elementSize.mode = ElementSizeMode.SET_MODE
        elementSize.size = IntSize(width, height)

        drawerChain.add(buildDrawer { canvas, drawerContext ->
            canvas.restore()

            val rRect = RRect.makeLTRB(
                padding.left.toFloat(), padding.top.toFloat(),
                width.toFloat() + tlRad, height.toFloat() + brRad,
                tlRad, trRad, brRad, blRad
            )

            canvas.drawRRect(rRect, paint.apply {
                rRectShadowInfo?.let {
                    imageFilter = it.getDropShadowImageFilter()
                }
            })
            PaddingDrawer().draw(canvas, drawerContext)
        })
    }
}