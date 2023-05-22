package org.sereinfish.catcat.image.tool.core.element

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.measure.Padding
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSize
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.core.shadow.ShadowInfo
import org.sereinfish.catcat.image.tool.draw.DrawerChain

interface Element {
    var bitmap: Bitmap? // 图像

    var parent: Element? // 父节点
    var elementSize: ElementSize // 大小
    val subElements: MutableList<Element> // 子元素

    var shadowInfo: ShadowInfo? // 阴影
    var padding: Padding // 内边距
    var backgroundColor: Int? // 背景颜色

    // 绘制链
    val drawerChain: DrawerChain

    /**
     * 添加子元素
     */
    fun add(element: Element){
        element.parent = this
        subElements.add(element)
    }

    /**
     * 获取自己的大小
     */
    fun getSize(): IntSize = elementSize.getSize(this)

    /**
     * 获取布局占用大小
     */
    fun getLayoutSize() = IntSize().apply {
        add(getSize())
        add(padding.getSize())
    }

    /**
     * 绘制处理
     */
    fun draw(canvas: Canvas, context: DrawerContext){
        drawerChain.draw(canvas, context)
    }

    /**
     * 获取自己的画布
     */
    fun getCanvas(): Canvas {
        return bitmap?.let {
            Canvas(it)
        }?: kotlin.run {
            val mBitmap = Bitmap().also {
                val size = getLayoutSize()
                val imageInfo = ImageInfo(size.width, size.height, ColorType.BGRA_8888, ColorAlphaType.PREMUL)
                it.allocPixels(imageInfo, imageInfo.minRowBytes)
            }
            bitmap = mBitmap
            Canvas(mBitmap)
        }
    }
}