package org.sereinfish.catcat.image.tool.builder.drawer.background

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.draw.Drawer
import org.sereinfish.catcat.image.tool.core.element.Element
import org.sereinfish.catcat.image.tool.core.element.layout.Layout
import org.sereinfish.catcat.image.tool.utils.paint
import org.sereinfish.catcat.image.tool.utils.toImage
import kotlin.math.roundToInt

class BackgroundBlurDrawer(
    val sigmaX: Float,
    val sigmaY: Float,
    val mode: FilterTileMode = FilterTileMode.CLAMP
): Drawer {
    constructor(sigma: Float, mode: FilterTileMode = FilterTileMode.CLAMP): this(sigma, sigma, mode)

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
                paint {
                    imageFilter = ImageFilter.makeBlur(sigmaX, sigmaY, this@BackgroundBlurDrawer.mode)
                }.use {
                    canvas.drawImage(bitmap.toImage(), 0f, 0f, it)
                }
            }
        }
    }
}

fun Element.setBlurBackground(sigma: Float, mode: FilterTileMode = FilterTileMode.CLAMP){
    backgroundDrawer.add(BackgroundBlurDrawer(sigma, mode))
}

fun Element.setBlurBackground(sigmaX: Float, sigmaY: Float, mode: FilterTileMode = FilterTileMode.CLAMP){
    backgroundDrawer.add(BackgroundBlurDrawer(sigmaX, sigmaY, mode))
}