package org.sereinfish.catcat.image.tool.builder.drawer

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.draw.Drawer
import org.sereinfish.catcat.image.tool.utils.paint

class ElementShadowDrawer: Drawer {
    override fun draw(canvas: Canvas, context: DrawerContext) {
        context.element.shadowInfo?.let { shadowInfo ->
            // 绘制阴影
            val size = context.element.getLayoutSize()
            paint {
                imageFilter = shadowInfo.getDropShadowOnlyImageFilter()
            }.use { paint ->
                val rect = Rect.makeXYWH(
                    context.element.padding.left.toFloat(),
                    context.element.padding.top.toFloat(),
                    (size.width - context.element.padding.left - context.element.padding.right).toFloat(),
                    (size.height - context.element.padding.top - context.element.padding.bottom).toFloat()
                )

                canvas.drawRect(rect, paint)
            }
        }
    }
}