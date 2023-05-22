package org.sereinfish.catcat.image.tool.element

import org.jetbrains.skija.ImageFilter
import org.jetbrains.skija.Paint
import org.sereinfish.catcat.image.tool.builder.drawer.PaddingDrawer
import org.sereinfish.catcat.image.tool.builder.drawer.buildDrawer
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSizeMode
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.core.shadow.ShadowInfo
import org.sereinfish.catcat.image.tool.utils.paint

/**
 * 圆形
 */
class CircleElement(
    var radius: Int,
    var paint: Paint,
    var circleShadow: ShadowInfo? = null, // 阴影
): AbstractElement() {
    constructor(radius: Int, color: Int): this(radius, paint {
        setColor(color)
    })

    init {
        drawerChain.add(buildDrawer { canvas, drawerContext ->
            canvas.restore()
            canvas.drawCircle(
                radius.toFloat() + padding.left,
                radius.toFloat() + padding.top,
                radius.toFloat(), paint.apply {
                    circleShadow?.let {
                        imageFilter = ImageFilter.makeDropShadow(
                            it.dx.toFloat(),
                            it.dy.toFloat(),
                            it.sigmaX.toFloat(),
                            it.sigmaY.toFloat(),
                            it.color
                        )
                    }
                }
            )

            PaddingDrawer().draw(canvas, drawerContext)
        })
    }

    override fun getSize(): IntSize {
        return if (elementSize.mode == ElementSizeMode.DEFINED_MODE)
            IntSize().apply {
                width = radius * 2
                height = radius * 2
            }
        else elementSize.getSize(this)
    }
}