package org.sereinfish.catcat.image.tool.builder.drawer

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.builder.ElementBuilder
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.layout.Layout
import org.sereinfish.catcat.image.tool.draw.Drawer
import org.sereinfish.catcat.image.tool.utils.DrawerLevel
import org.sereinfish.catcat.image.tool.utils.paint
import org.sereinfish.catcat.image.tool.utils.toImage
import kotlin.math.roundToInt

class BlurBackgroundDrawer(
    val sigmaX: Float,
    val sigmaY: Float,
    val mode: FilterTileMode = FilterTileMode.CLAMP
): Drawer {
    constructor(sigma: Float, mode: FilterTileMode = FilterTileMode.CLAMP): this(sigma, sigma, mode)

    override fun draw(canvas: Canvas, context: DrawerContext) {
        context.parentElement?.let { parentElement ->
            if (parentElement is Layout){
                val padding = context.element.padding
                val size = context.element.getSize()
                // 获取此组件在父组件的位置
                val offset = parentElement.getSubElementOffset(parentElement.subElements.indexOf(context.element))
                // 生成画布
                val bitmap = Bitmap().apply {
                    val imageInfo = ImageInfo(size.width, size.height, ColorType.BGRA_8888, ColorAlphaType.PREMUL)
                    allocPixels(imageInfo, imageInfo.minRowBytes)
                }
                // 读取内容
                context.parentCanvas?.readPixels(bitmap,
                    (offset.x + padding.left).roundToInt(),
                    (offset.y + padding.top).roundToInt()
                )
                // 绘制背景
                paint {
                    imageFilter = ImageFilter.makeBlur(5f, 5f, this@BlurBackgroundDrawer.mode)
                }.use {
                    canvas.drawImage(bitmap.toImage(), padding.left.toFloat(), padding.top.toFloat(), it)
                }
            }
        }
    }
}

fun ElementBuilder.blurBackground(
    sigmaX: Float,
    sigmaY: Float,
    mode: FilterTileMode = FilterTileMode.CLAMP,
    level: Int = DrawerLevel.HIGH
){
    addDrawer(BlurBackgroundDrawer(sigmaX, sigmaY, mode), level)
}

fun ElementBuilder.blurBackground(
    sigma: Float,
    mode: FilterTileMode = FilterTileMode.CLAMP,
    level: Int = DrawerLevel.HIGH
){
    addDrawer(BlurBackgroundDrawer(sigma, mode), level)
}