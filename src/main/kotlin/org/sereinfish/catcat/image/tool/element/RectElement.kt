package org.sereinfish.catcat.image.tool.element

import org.jetbrains.skija.Paint
import org.jetbrains.skija.Rect
import org.sereinfish.catcat.image.tool.builder.drawer.buildDrawer
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSizeMode
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.utils.paint

class RectElement(
    val width: Int,
    val height: Int,
    val paint: Paint = paint()
): AbstractElement() {
    constructor(width: Int, height: Int, color: Int): this(width, height, paint {
        setColor(color)
    })

    init {
        elementSize.mode = ElementSizeMode.SET_MODE
        elementSize.size = IntSize(width, height)

        drawerChain.add(buildDrawer { canvas, drawerContext ->
            canvas.drawRect(Rect.makeWH(width.toFloat(), height.toFloat()), paint)
        })
    }


}