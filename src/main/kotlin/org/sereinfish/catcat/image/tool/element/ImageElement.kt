package org.sereinfish.catcat.image.tool.element

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSizeMode
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.draw.Drawer
import org.sereinfish.catcat.image.tool.utils.toBitmap
import org.sereinfish.catcat.image.tool.utils.toImage


class ImageElement(
    var image: Image,
    var cropImageMode: CropImageMode = CropImageMode.Fit,
    var paint: Paint = org.sereinfish.catcat.image.tool.utils.paint()
): AbstractElement() {

    init {
        drawerChain.add(object : Drawer {
            override fun draw(canvas: Canvas, context: DrawerContext) {
                canvas.drawImage(cropImageMode.getImage(cropImageMode, image, getSize()), 0f, 0f, paint)
            }
        })
    }

    override fun getSize(): IntSize {
        return if (elementSize.mode == ElementSizeMode.DEFINED_MODE)
            IntSize(image.width, image.height)
        else elementSize.getSize(this)
    }
}

enum class CropImageMode{
    /**
     * 均匀缩放图片，并保持宽高比（默认）。
     * 如果内容小于指定大小，系统会放大图片以适应边界。
     */
    Fit,

    // 将图片居中裁剪到可用空间。
    Crop,

    // 缩放来源图片，保持宽高比不变，使边界与目标高度匹配。
    FillHeight,

    // 缩放来源图片，保持宽高比不变，使边界与目标宽度匹配。
    FillWidth,

    /**
     * 以非均匀方式垂直和水平缩放内容，以填充目标边界。
     * （注意：如果将图片放入与其宽高比不完全相符的容器中，则图片会失真）
     */
    FillBounds,

    /**
     * 缩放来源图片，使宽高保持在目标边界内。
     * 如果来源图片在两个维度上都小于或等于目标，则其行为类似于“None”。
     * 内容始终包含在边界内。如果内容小于边界，则不会应用缩放。
     */
    Inside,

    /**
     * 不对来源图片应用任何缩放。如果内容小于目标边界，则不会缩放以适应相应区域。
     */
    None;

    /**
     * 获取处理后图片
     */
    fun getImage(mode: CropImageMode, image: Image, layoutSize: IntSize): Image {
        val layoutBitmap = Bitmap().apply {
            val imageInfo = ImageInfo(layoutSize.width, layoutSize.height, ColorType.BGRA_8888, ColorAlphaType.PREMUL)
            allocPixels(imageInfo, imageInfo.minRowBytes)
        }
        Canvas(layoutBitmap).use { canvas ->
            when(mode){
                Fit -> {
                    // 计算图片和布局的宽高比
                    val imageScale = image.width / image.height.toFloat()
                    val layoutScale = layoutSize.width / layoutSize.height.toFloat()

                    if (imageScale >= layoutScale){
                        // 以布局宽为准
                        val scale = layoutSize.width / image.width.toFloat()
                        val imageHeight = scale * image.height
                        val y = (layoutSize.height - imageHeight) / 2f

                        canvas.save()
                        canvas.scale(scale, scale)
                        canvas.drawImage(image, 0f, y / scale)
                        canvas.restore()
                    }else {
                        // 以布局高为准
                        val scale = layoutSize.height / image.height.toFloat()
                        val imageWidth = scale * image.width
                        val x = (layoutSize.width - imageWidth) / 2f

                        canvas.save()
                        canvas.scale(scale, scale)
                        canvas.drawImage(image, x, 0f)
                        canvas.restore()
                    }
                }
                else -> {}
            }
        }

        return layoutBitmap.toImage()
    }
}