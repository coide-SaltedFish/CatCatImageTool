package org.sereinfish.catcat.image.tool.element

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.draw.Drawer
import org.sereinfish.catcat.image.tool.utils.paint

class TestShadowElement: AbstractElement() {
    init {
        drawerChain.add(object : Drawer{
            override fun draw(canvas: Canvas, context: DrawerContext) {
                canvas.drawRect(Rect.makeWH(getSize().width.toFloat(), getSize().height.toFloat()), paint {
                    color = Color.makeRGB(255, 0, 0)
                    imageFilter = ImageFilter.makeDistantLitDiffuse(
                        -3f, -2f, 5f,
                        Color.makeRGB(0, 255, 0),
                        0.8f,
                        0.2f,
                        null, null
                    )
                })
            }
        })
    }
}