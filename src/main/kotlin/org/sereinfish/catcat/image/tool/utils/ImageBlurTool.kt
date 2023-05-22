package org.sereinfish.catcat.image.tool.utils

import org.jetbrains.skija.Bitmap
import org.jetbrains.skija.Canvas
import org.jetbrains.skija.Paint
import java.util.*

object ImageBlurTool {
    /**
     * 图片像素随机扩散效果
     */
    fun spread(image: Bitmap, radius: Int): Bitmap{
        val quantum: Int = radius
        val newBitmap = image.makeClone()

        val rand = Random(System.currentTimeMillis())

        Canvas(newBitmap).use { canvas ->
            val paint = Paint()
            for (y in 0 until image.height){
                for (x in 0 until image.width){
                    var xDistance: Float
                    var yDistance: Float
                    do {
                        xDistance = (((2 * radius.toDouble() + 1) * rand.nextDouble()) - quantum).toFloat()
                        yDistance = (((2 * radius.toDouble() + 1) * rand.nextDouble()) - quantum).toFloat()
                    }while ((x + xDistance < 0 || y + yDistance < 0) || x + xDistance > image.width
                        || y + yDistance > image.height
                    )
                    paint.color = image.getColor((x + xDistance).toInt(), (y + yDistance).toInt())
                    canvas.drawPoint(x.toFloat(), y.toFloat(), paint)
                }
            }
        }

        return newBitmap
    }
}