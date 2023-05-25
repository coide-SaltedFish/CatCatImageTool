package org.sereinfish.catcat.image.tool.builder.drawer.background

import org.jetbrains.skija.Bitmap
import org.jetbrains.skija.Canvas
import org.jetbrains.skija.ImageFilter
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.draw.Drawer
import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.core.element.layout.Layout
import org.sereinfish.catcat.image.tool.utils.paint
import org.sereinfish.catcat.image.tool.utils.toImage
import kotlin.math.roundToInt

/**
 * 设置背景为父组件
 */
class BackgroundParentImageDrawer: Drawer {
    override fun draw(canvas: Canvas, context: DrawerContext) {
        context.parentElement?.let { parentElement ->
            if (parentElement is Layout){
                // 获取此组件在父组件的位置
                val offset = parentElement.getSubElementOffset(parentElement.subElementList.indexOf(context.element))
                // 生成画布
                val bitmap = Bitmap().apply {
                    val imageInfo = context.element.getImageInfo()
                    allocPixels(imageInfo, imageInfo.minRowBytes)
                }
                // 读取内容
                context.parentCanvas?.readPixels(bitmap, offset.x.roundToInt(), offset.y.roundToInt())
                // 绘制背景
                canvas.drawImage(bitmap.toImage(), 0f, 0f, paint())
            }
        }
    }
}

fun Element.setBackgroundParentImage(){
    backgroundDrawer.add(BackgroundParentImageDrawer())
}