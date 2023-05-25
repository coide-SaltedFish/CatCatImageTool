package org.sereinfish.catcat.image.tool.element

import org.jetbrains.skija.BlendMode
import org.jetbrains.skija.Color
import org.jetbrains.skija.Image
import org.jetbrains.skija.Paint
import org.jetbrains.skija.RRect
import org.sereinfish.catcat.image.tool.builder.drawer.buildDrawer
import org.sereinfish.catcat.image.tool.core.element.AbstractElement
import org.sereinfish.catcat.image.tool.core.measure.CropImageMode
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.utils.paint
import kotlin.math.roundToInt

class ImageElement(
    var image: Image,
    var cropImageMode: CropImageMode = CropImageMode.Fit,
    var paint: Paint = paint(),
    var scale: Float = 1f,
): AbstractElement() {

    init {
        drawChain.add(buildDrawer { canvas, context ->
            val cropImage = cropImageMode.getImage(image, getSize())
            canvas.drawImage(
                cropImage,
                padding.left.toFloat(), padding.top.toFloat(),
                paint
            )
        })
    }

    override fun getAdaptiveSize(): IntSize {
        return IntSize((image.width * scale).roundToInt(), (image.height * scale).roundToInt())
    }
}