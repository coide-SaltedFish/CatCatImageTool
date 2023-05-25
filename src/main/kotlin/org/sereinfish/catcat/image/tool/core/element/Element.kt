package org.sereinfish.catcat.image.tool.core.element

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.draw.DrawChain
import org.sereinfish.catcat.image.tool.core.element.layout.Layout
import org.sereinfish.catcat.image.tool.core.measure.rect.IntRectSize
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSize
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize

interface Element {
    var bitmap: Bitmap? // 图像

    var parent: Layout? // 父节点
    var parentIndex: Int? // 在父节点中的位置

    var padding: IntRectSize
    var margin: IntRectSize
    // 元素大小
    var elementSize: ElementSize

    // 绘制链
    var backgroundDrawer: DrawChain // 背景绘制
    var drawChain: DrawChain // 控件绘制
    var foregroundDrawer: DrawChain // 前景绘制链

    /**
     * 控件绘制
     */
    fun draw(canvas: Canvas, context: DrawerContext)

    /**
     * 获取元素大小
     *
     */
    fun getSize(): IntSize{
        return elementSize.getSize(this)
    }

    /**
     * 设置元素大小
     */
    fun setSize(width: Int, height: Int){
        elementSize.heightSize.mode = ElementSize.SizeMode.SET
        elementSize.widthSize.mode = ElementSize.SizeMode.SET
        elementSize.heightSize.value = height
        elementSize.widthSize.value = width
    }

    fun setSize(size: Int){
        elementSize.heightSize.mode = ElementSize.SizeMode.SET
        elementSize.widthSize.mode = ElementSize.SizeMode.SET
        elementSize.heightSize.value = size
        elementSize.widthSize.value = size
    }

    fun setWidth(size: Int){
        elementSize.widthSize.mode = ElementSize.SizeMode.SET
        elementSize.widthSize.value = size
    }

    fun setHeight(size: Int){
        elementSize.heightSize.mode = ElementSize.SizeMode.SET
        elementSize.heightSize.value = size
    }

    fun setMaxWidth(){
        elementSize.widthSize.mode = ElementSize.SizeMode.MAX
    }

    fun setMaxHeight(){
        elementSize.widthSize.mode = ElementSize.SizeMode.MAX
    }

    fun setAdaptiveWidth(){
        elementSize.widthSize.mode = ElementSize.SizeMode.ADAPTIVE
    }

    fun setAdaptiveHeight(){
        elementSize.widthSize.mode = ElementSize.SizeMode.ADAPTIVE
    }

    /**
     * 获取布局大小
     */
    fun getLayoutSize(): IntSize{
        return getSize().apply {
            add(padding.getSize())
        }
    }

    /**
     * 获取元素自适应大小
     */
    fun getAdaptiveSize(): IntSize

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

    fun getImageInfo() = let {
        val size = getLayoutSize()
        ImageInfo(size.width, size.height, ColorType.BGRA_8888, ColorAlphaType.PREMUL)
    }
}