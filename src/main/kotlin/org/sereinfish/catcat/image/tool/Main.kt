package org.sereinfish.catcat.image.tool

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.builder.buildImage
import org.sereinfish.catcat.image.tool.builder.drawer.background.setBackgroundColor
import org.sereinfish.catcat.image.tool.builder.drawer.background.setBackgroundImage
import org.sereinfish.catcat.image.tool.builder.drawer.background.setBackgroundParentImage
import org.sereinfish.catcat.image.tool.builder.drawer.background.setBlurBackground
import org.sereinfish.catcat.image.tool.builder.drawer.buildDrawer
import org.sereinfish.catcat.image.tool.builder.drawer.foreground.setForegroundFrameStroke
import org.sereinfish.catcat.image.tool.builder.element.layout.column
import org.sereinfish.catcat.image.tool.builder.element.setTextShadow
import org.sereinfish.catcat.image.tool.builder.element.text
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.core.measure.CropImageMode
import org.sereinfish.catcat.image.tool.core.measure.ShadowInfo
import org.sereinfish.catcat.image.tool.core.measure.rect.IntRectSize
import org.sereinfish.catcat.image.tool.element.ImageElement
import org.sereinfish.catcat.image.tool.element.TextElement
import org.sereinfish.catcat.image.tool.element.layout.ColumnLayout
import org.sereinfish.catcat.image.tool.utils.loadImageByFile
import org.sereinfish.catcat.image.tool.utils.paint
import org.sereinfish.catcat.image.tool.utils.toImage
import org.sereinfish.catcat.image.tool.utils.writeImageByFile
import java.io.File
import java.io.FileOutputStream

fun main() {
//    println("1234".substring(0, 0))
//    test()
    val image = buildImage {
        setHeight(200)
        setBackgroundImage(loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\aad.jpg")), CropImageMode.Crop)
        alignment = Alignment.CENTER_VERTICAL
        column {
            alignment = Alignment.RIGHT
            setBackgroundParentImage()
            text("Hello World", Font(Typeface.makeFromName("黑体", FontStyle.BOLD), 32f)){
                padding = IntRectSize(10)
                alignment = Alignment.CENTER
                setTextShadow(ShadowInfo(2, 1))
                setBlurBackground(2f)
                setForegroundFrameStroke(Color.makeARGB(100, 96, 96, 96))
            }
            column {
                setSize(10)
            }
            text("CatCat Image Tool", Font(Typeface.makeFromName("微软雅黑", FontStyle.NORMAL), 18f)){
                alignment = Alignment.RIGHT
                padding = IntRectSize(3)
                setMaxWidth()
                setBackgroundColor(Color.makeARGB(125, 255, 255, 255))
                setTextShadow(ShadowInfo(2, 1))
            }
        }
    }
    writeImageByFile(image, File("./test.png"))
}

fun test(){
    val text = TextElement("123", Font(Typeface.makeFromName("黑体", FontStyle.BOLD), 32f)).apply {
        padding = IntRectSize(10)
        paint.apply {
            imageFilter = ImageFilter.makeDropShadow(2f, 2f, 1f, 1f, Color.makeRGB(96,96,96))
        }
        backgroundDrawer.add(buildDrawer { canvas, context ->
            canvas.clear(Color.makeRGB(0,0,0))
            canvas.drawRRect(
                RRect.makeLTRB(0f, 0f, getLayoutSize().width.toFloat(), getLayoutSize().height.toFloat(), 10f),
                paint {
                    color = Color.makeRGB(255, 255, 255)
                }
            )
        })
        foregroundDrawer.add(buildDrawer { canvas, context ->
            canvas.drawRect(getLayoutSize().getRect(), paint {
                mode = PaintMode.STROKE
                color = Color.makeRGB(255, 0, 0)
                strokeWidth = 1f
            })
        })
    }

    val text2 = TextElement("HELLO", Font(Typeface.makeFromName("黑体", FontStyle.BOLD), 20f)).apply {
        foregroundDrawer.add(buildDrawer { canvas, context ->
            canvas.drawRect(getLayoutSize().getRect(), paint {
                mode = PaintMode.STROKE
                color = Color.makeRGB(255, 0, 0)
                strokeWidth = 1f
            })
        })
        alignment = Alignment.RIGHT
//        elementSize.heightSize = ElementSize.SizeValue(
//            ElementSize.ValueMode.HEIGHT,
//            ElementSize.SizeMode.MAX,
//        )
//        elementSize.widthSize = ElementSize.SizeValue(
//            ElementSize.ValueMode.WIDTH,
//            ElementSize.SizeMode.MAX,
//        )
    }

    val image = ImageElement(loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\aad.jpg"))).apply {
        padding = IntRectSize(20)

        backgroundDrawer.add(buildDrawer { canvas, context ->
            canvas.clear(Color.makeRGB(0,0,0))
            canvas.drawRRect(
                RRect.makeLTRB(0f, 0f, getLayoutSize().width.toFloat(), getLayoutSize().height.toFloat(), 10f),
                paint {
                    color = Color.makeRGB(255, 255, 255)
                }
            )
        })

        foregroundDrawer.add(buildDrawer { canvas, context ->
            // 创建新画布
            val surface = Surface.makeRaster(getImageInfo())
            surface.canvas.let { newCanvas ->
                newCanvas.drawRRect(RRect.makeLTRB(padding.left.toFloat(), padding.top.toFloat(), getSize().width.toFloat(), getSize().height.toFloat(), 10f), paint {
                })
                newCanvas.drawImage(bitmap!!.toImage(), 0f, 0f, paint {
                    setBlendMode(BlendMode.SRC_IN)
                })
            }
            canvas.clear(Color.makeARGB(255,255,255,255))
            canvas.drawImage(surface.makeImageSnapshot(), 0f, 0f, paint {
                imageFilter = ImageFilter.makeDropShadow(5f, 5f, 5f, 5f, Color.makeRGB(96,96,96))
            })
        })

        foregroundDrawer.add(buildDrawer { canvas, context ->
            canvas.drawRect(getLayoutSize().getRect(), paint {
                mode = PaintMode.STROKE
                color = Color.makeRGB(255, 0, 0)
                strokeWidth = 1f
            })
        })
        setWidth(100)
        setHeight(100)
        paint.apply {
            imageFilter = ImageFilter.makeDropShadow(5f, 5f, 5f, 5f, Color.makeRGB(96,96,96))
        }
        cropImageMode = CropImageMode.Fit
    }

    val layout = ColumnLayout().apply {
        alignment = Alignment.CENTER
        foregroundDrawer.add(buildDrawer { canvas, context ->
            canvas.drawRect(getLayoutSize().getRect(), paint {
                mode = PaintMode.STROKE
                color = Color.makeRGB(255, 0, 0)
                strokeWidth = 1f
            })
        })
//        elementSize.heightSize = ElementSize.SizeValue(
//            ElementSize.ValueMode.HEIGHT,
//            ElementSize.SizeMode.SET,
//            200
//        )
    }
    layout.add(text)
    layout.add(text2)
    layout.add(image)

    layout.draw(layout.getCanvas(), DrawerContext(layout))

    FileOutputStream(File("./test.png")).use {
        it.write(layout.bitmap!!.toImage().encodeToData(EncodedImageFormat.PNG)!!.bytes)
    }
}

