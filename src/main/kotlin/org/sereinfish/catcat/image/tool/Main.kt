package org.sereinfish.catcat.image.tool

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.builder.buildImage
import org.sereinfish.catcat.image.tool.builder.drawer.background.setBackgroundColor
import org.sereinfish.catcat.image.tool.builder.drawer.background.setBackgroundImage
import org.sereinfish.catcat.image.tool.builder.drawer.background.setBackgroundParentImage
import org.sereinfish.catcat.image.tool.builder.drawer.background.setBlurBackground
import org.sereinfish.catcat.image.tool.builder.drawer.buildDrawer
import org.sereinfish.catcat.image.tool.builder.drawer.foreground.setForegroundFrameStroke
import org.sereinfish.catcat.image.tool.builder.element.layout.buildColumnLayout
import org.sereinfish.catcat.image.tool.builder.element.layout.buildRowLayout
import org.sereinfish.catcat.image.tool.builder.element.layout.column
import org.sereinfish.catcat.image.tool.builder.element.layout.row
import org.sereinfish.catcat.image.tool.builder.element.setTextColor
import org.sereinfish.catcat.image.tool.builder.element.setTextShadow
import org.sereinfish.catcat.image.tool.builder.element.text
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.core.measure.CropImageMode
import org.sereinfish.catcat.image.tool.core.measure.ShadowInfo
import org.sereinfish.catcat.image.tool.core.measure.rect.IntRectSize
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.element.ImageElement
import org.sereinfish.catcat.image.tool.element.TextElement
import org.sereinfish.catcat.image.tool.element.layout.ColumnLayout
import org.sereinfish.catcat.image.tool.utils.loadImageByFile
import org.sereinfish.catcat.image.tool.utils.paint
import org.sereinfish.catcat.image.tool.utils.toImage
import org.sereinfish.catcat.image.tool.utils.writeImageByFile
import java.io.File
import java.io.FileOutputStream

val fontType = Typeface.makeFromName("黑体", FontStyle.NORMAL)
fun main() {
    val image = buildImage {
        val width = 1345f
        val height = 935f

        setSize(width.toInt(), height.toInt())

        setBackgroundColor(Color.makeRGB(0,0,0))
        backgroundDrawer.add(buildDrawer { canvas, context ->
            canvas.drawRRect(RRect(0f, 0f, width, height, floatArrayOf(0f, 35f, 0f, 0f)),
                paint {
                    color = Color.makeRGB(212, 212, 212)
                }
            )

            // 十字交叉线
            canvas.drawRect(Rect.makeXYWH(0f, 8f, 205f, 1f), paint {
                color = Color.makeRGB(177, 177, 177)
            })
            canvas.drawRect(Rect.makeXYWH(8f, 0f, 1f, width), paint {
                color = Color.makeRGB(177, 177, 177)
            })
            // 左上角圆
            canvas.drawCircle(30f, 30f, 55f, paint {
                mode = PaintMode.STROKE
                color = Color.makeRGB(177, 177, 177)
                strokeWidth = 1f
            })
            // 上部的粗线
            canvas.drawRect(Rect.makeXYWH(0f, 110f, width, 2f), paint {
                color = Color.makeRGB(177, 177, 177)
            })

            // 拖动条
            canvas.drawRect(Rect.makeXYWH(width - 42, 126f, 5f, height - 37 - 126), paint {
                color = Color.makeRGB(194, 192, 194)
            })
            canvas.drawRect(Rect.makeXYWH(width - 42, height - 37 - 385, 5f, 385f), paint {
                color = Color.makeRGB(82, 82, 82)
            })

            // 下部的粗线
            canvas.drawRect(Rect.makeXYWH(57f, height - 62f, width - (2 * 57), 2f), paint {
                color = Color.makeRGB(177, 177, 177)
            })
        })

        column {
            setMaxHeight()
            setMaxWidth()

            column {
                setHeight(112)
                setMaxWidth()

                padding = IntRectSize(left = 40, top = 30)

                text("猫猫", Font(fontType, 27f))
                text("abcdefg", Font(fontType, 20f)){
                    padding = IntRectSize(top = 11)
                    setTextColor(Color.makeRGB(115, 115, 115))
                }
            }

            column {
                foregroundDrawer.add(buildDrawer { canvas, context ->
                    canvas.drawRect(Rect(0f, 0f, getSize().width.toFloat(), 10f), paint {
                        shader = Shader.makeLinearGradient(Point(0f, 0f), Point(0f, 15f), intArrayOf(
                            Color.makeARGB(200, 212, 212, 212),
                            Color.makeARGB(0, 212, 212, 212)
                        ))
                    })
                })

                padding = IntRectSize(bottom = 100, left = 60, right = 85)
                alignment = Alignment.BOTTOM
                setMaxWidth()
                setMaxHeight()

                add(chatRow(
                    loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\auisiud.jpg")),
                    "猫猫", "你好，我是ABCDEFGabcdefg12344"
                ))

                add(chatRow(
                    loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\auisiud.jpg")),
                    "猫猫", "你好，我是ABCDEFGabcdefg12344"
                ))

                add(chatRow(
                    loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\auisiud.jpg")),
                    "猫猫", "你好，我是ABCDEFGabcdefg12344"
                ))

                add(chatRow(
                    loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\auisiud.jpg")),
                    "猫猫", "你好，我是ABCDEFGabcdefg12344"
                ))

                add(chatRow(
                    loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\auisiud.jpg")),
                    "猫猫", "你好，我是ABCDEFGabcdefg12344"
                ))


                add(chatRow(
                    loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\auisiud.jpg")),
                    "猫猫", "你好，我是ABCDEFGabcdefg12344"
                ))

                add(chatRow(
                    loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\auisiud.jpg")),
                    "猫猫", "你好，我是ABCDEFGabcdefg12344"
                ))


                add(chatRow(
                    loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\auisiud.jpg")),
                    "猫猫", "你好，我是ABCDEFGabcdefg12344"
                ))

                add(chatRow(
                    loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\auisiud.jpg")),
                    "猫猫", "你好，我是ABCDEFGabcdefg12344",
                    true
                ))
            }
        }
    }

    writeImageByFile(image, File("./test.png"))
}

// 头像宽度 84f
// 左边头像距边框 60 右边 84
// 头像加聊天框高 101 玩家名字 20
// 两条聊天间距 28
// 头像距离名字聊天框 22
// 名字距离聊天框 12
// 聊天框圆角 15 高度 58 字体 25

/**
 * 聊天的一列
 */
fun chatRow(image: Image, name: String, message: String, isSender: Boolean = false) = buildRowLayout {
    setHeight(101)
    setMaxWidth()
    padding = IntRectSize(top = 28)
    if (isSender){
        alignment = Alignment.RIGHT
        add(messageRect(name, message, isSender))
        add(headImage(image))
    }else {
        alignment = Alignment.LEFT

        add(headImage(image))
        add(messageRect(name, message, isSender))
    }
}

fun messageRect(name: String, message: String, isSender: Boolean = false) = buildColumnLayout {
    padding = IntRectSize(left = 22)
    if (isSender){
        alignment = Alignment.RIGHT
    }

    text(name, Font(fontType, 20f)){
        padding = IntRectSize(bottom = 12)
        setTextColor(Color.makeRGB(121, 121,121))
    }
    row {
        padding = IntRectSize(15)
        text(message, Font(fontType, 25f)){
            setTextColor(Color.makeRGB(18, 18,18))
        }
        // 绘制背景
        backgroundDrawer.add(buildDrawer { canvas, context ->
            val size = context.parentElement?.getSize() ?: IntSize(10)
            val radii = if (isSender)
                floatArrayOf(15f, 0f, 15f, 15f)
            else floatArrayOf(0f, 15f, 15f, 15f)

            val rRect = RRect(0f, 0f, size.width.toFloat(), 58f, radii)

            canvas.drawRRect(rRect, paint {
                color = Color.makeRGB(235, 235, 235)
            })
        })
    }

}


/**
 * 头像
 */
fun headImage(image: Image) = // 头像
    buildColumnLayout {
        alignment = Alignment.TOP
        setWidth(84)
        setHeight(101)
        drawChain.add(buildDrawer { canvas, context ->
            canvas.drawCircle(42f, 42f, 42f, paint())
            canvas.drawImage(CropImageMode.Crop.getImage(image, IntSize(84, 84)), 0f, 0f, paint {
                setBlendMode(BlendMode.SRC_IN)
            })
        })
    }