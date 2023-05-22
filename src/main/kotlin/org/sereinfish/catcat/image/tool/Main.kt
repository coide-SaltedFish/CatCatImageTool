package org.sereinfish.catcat.image.tool

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.builder.buildImage
import org.sereinfish.catcat.image.tool.builder.drawer.*
import org.sereinfish.catcat.image.tool.builder.element.*
import org.sereinfish.catcat.image.tool.builder.layout.*
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.core.measure.Padding
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSizeMode
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.core.shadow.ShadowInfo
import org.sereinfish.catcat.image.tool.element.ImageElement
import org.sereinfish.catcat.image.tool.element.TestElement
import org.sereinfish.catcat.image.tool.element.TextElement
import org.sereinfish.catcat.image.tool.layout.ColumnLayout
import org.sereinfish.catcat.image.tool.utils.SortedList
import org.sereinfish.catcat.image.tool.utils.loadImageByFile
import org.sereinfish.catcat.image.tool.utils.paint
import org.sereinfish.catcat.image.tool.utils.toImage
import java.io.File
import java.io.FileOutputStream


fun main() {
    test5()
}

fun test5(){
    val fontType = Typeface.makeFromName("微软雅黑", FontStyle.NORMAL)

    val image = buildImage {
        addAbs {
            addImage(loadImageByFile(File("C:\\Users\\MI\\Pictures\\Saved Pictures\\70858371_p0_master1200.jpg")))

            floatOffset(700f, 170f) put rect(IntSize(40), Color.makeRGB(0, 255, 0))

            floatOffset(710f, 190f) put column({
                blurBackground(10f, level = 2)
                frameStroke(Color.makeARGB(100, 97, 97, 97), 1)
                alignment = Alignment.CENTER_HORIZONTAL
            }) {
                + text("你好", Font(fontType, 30f)){
                    textElement.textColor = Color.makeARGB(200, 255, 255, 255)
                    textElement.textPadding = Padding(10)
                    textElement.textShadow = ShadowInfo(2, 1)
                }
                + text("Hello World", Font(fontType, 30f)){
                    textElement.textColor = Color.makeARGB(200, 255, 255, 255)
                    textElement.textPadding = Padding(10)
                    textElement.textShadow = ShadowInfo(2, 1)
                }

                + row({
                    alignment = Alignment.CENTER_VERTICAL
                }) {
                    + image(loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\CLY2CD}6%41ND)KR3RNK4UN.jpg"))){
                        padding = Padding(20)
//                        element.shadowInfo = ShadowInfo(2, 3)
                        setRoundRectShadow(15f, ShadowInfo(2, 3), 0)
                        setRoundRect(15f, 1)

                        setSize(80)
                    }

                    + image(loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\auisiud.jpg"))) {
                        padding = Padding(10)
                        element.shadowInfo = ShadowInfo(1, 4)
                        setSize(80)
                    }
                }
            }
        }
    }

    FileOutputStream(File("./test.png")).use {
        it.write(image.encodeToData(EncodedImageFormat.PNG)!!.bytes)
    }
}

fun test4(){
    val image = buildImage(buildBlock = {
        alignment = Alignment.CENTER_HORIZONTAL
        frameStroke(Color.makeRGB(255, 0, 0), 2)
    }) {
        addCircle(20, paint {
            color = Color.makeRGB(255, 0, 0)
        }){
            padding = Padding(20)
        }
    }

    FileOutputStream(File("./test.png")).use {
        it.write(image.encodeToData(EncodedImageFormat.PNG)!!.bytes)
    }
}

fun test3(){
    val image = buildImage(buildBlock = {
        alignment = Alignment.CENTER_HORIZONTAL
        frameStroke(Color.makeRGB(255, 0, 0), 2)
        backgroundColor = Color.makeRGB(0x4c, 0xaf, 0x50)
    }) {
        + text("上 123 一二三四", Font(Typeface.makeFromName("微软雅黑", FontStyle.NORMAL), 18f)){
            textElement.textShader = Shader.makeLinearGradient(0f, 0f, 60f, 0f, intArrayOf(-0xdb8460, -0xc0043))
                .makeWithColorFilter(ColorFilter.makeBlend(-0x33cccd, BlendMode.SCREEN))
        }

        addRow {
            addRRect(IntSize(40), 10f, Color.makeRGB(255, 255, 255)){
                padding = Padding(10)
                setRoundRectShadow(10f, ShadowInfo(4, 2), -2)
                frameStroke(Color.makeRGB(255, 0, 0), 2)
            }

            addRRect(IntSize(40), 10f, Color.makeRGB(255, 255, 255)){
                padding = Padding(10)
                rRectShadow = ShadowInfo(4, 2)
                frameStroke(Color.makeRGB(255, 0, 0), 2)
            }

            addRect(IntSize(40), Color.makeRGB(255, 255, 255)){
                padding = Padding(10)
                setRoundRect(10f, -1)
                setRoundRectShadow(10f, ShadowInfo(4, 2), -2)

                restore()

                frameStroke(Color.makeRGB(255, 0, 0), 2)
            }
        }

        + row({
            alignment = Alignment.CENTER_VERTICAL
        }) {
            + text("左", Font(Typeface.makeFromName("微软雅黑", FontStyle.NORMAL), 18f))
            + abs({
                setSize(300, 300)
                frameStroke(Color.makeRGB(0xff, 0xeb, 0x3b), 2)
            }) {
                floatOffset(0f, 0f) put image(loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\4e353a34c759b24.jpg"))){
                    setSizeMode(ElementSizeMode.FILL_MODE)
                    setRoundRect(30f, -1)
                }
                floatOffset(10f, 10f) put text("1234", Font(Typeface.makeFromName("微软雅黑", FontStyle.NORMAL), 18f)){
                    blurBackground(1f)
                    frameStroke(Color.makeARGB(100, 0x9e, 0x9e, 0x9e))
                }
                floatOffset(50f, 80f) put rect(IntSize(60, 60), Color.makeARGB(0 ,0 ,0, 0)){
                    blurBackground(5f)
                    frameStroke(Color.makeRGB( 0x9e, 0x9e, 0x9e))
                }
                floatOffset(60f, 90f) put text("1234", Font(Typeface.makeFromName("微软雅黑", FontStyle.NORMAL), 18f)){
                    textElement.textColor = Color.makeARGB(180, 0, 0, 0)
                    padding = Padding(10)
                    textElement.textShadow = ShadowInfo(2, 2)
                }

                floatOffset(20f, 150f) put circle(20, paint {
                    color = Color.makeARGB(80, 0, 255, 0)
                }, ShadowInfo(4, 2)){
                    frameStroke(Color.makeRGB( 0x9e, 0x9e, 0x9e))
                    padding = Padding(10)
                }
            }
            + text("右 Hamgjl", Font(Typeface.makeFromName("微软雅黑", FontStyle.NORMAL), 18f)){
                setSize(100)
                textElement.alignment = Alignment.CENTER
                padding = Padding(10)
                frameStroke(Color.makeRGB(0, 0, 255), 2)
            }
        }
        + text("下", Font(Typeface.makeFromName("微软雅黑", FontStyle.NORMAL), 18f)){
            textElement.textShadow = ShadowInfo(offset = 2, sigma = 1)
        }
    }

    FileOutputStream(File("./test.png")).use {
        it.write(image.encodeToData(EncodedImageFormat.PNG)!!.bytes)
    }
}

fun test(){
    val test = TestElement()
    test.elementSize.mode = ElementSizeMode.SET_MODE
    test.elementSize.size = IntSize(2000, 1000)

    test.draw(test.getCanvas(), DrawerContext(test))
    test.bitmap?.let { bitmap ->
        FileOutputStream(File("./test.png")).use {
            it.write(bitmap.toImage().encodeToData(EncodedImageFormat.PNG)!!.bytes)
        }
    }
}

fun test1(){
    val layout = ColumnLayout()
    layout.backgroundColor = Color.makeRGB(0x9e ,0x9e, 0x9e)

    layout.alignment = Alignment.CENTER_HORIZONTAL

    layout.add(TextElement("Hello", Font(Typeface.makeDefault(), 18f)).apply {
        padding = Padding(10)
        textShadow = ShadowInfo(offset = 2, sigma = 1)
    })
    layout.add(TextElement("World", Font(Typeface.makeFromName("微软雅黑", FontStyle.BOLD), 18f)).apply {
        textShader = Shader.makeLinearGradient(0f, 0f, 60f, 0f, intArrayOf(-0xdb8460, -0xc0043))
            .makeWithColorFilter(ColorFilter.makeBlend(-0x33cccd, BlendMode.SCREEN))
        textShadow = ShadowInfo(offset = 1, sigma = 1)
        padding = Padding(10)
        paintMode = PaintMode.STROKE
        strokeWidth = 1f
    })
    layout.add(TextElement("CatCat Image Tool", Font(Typeface.makeDefault(), 18f)).apply {
        padding = Padding(0)
    })
    layout.add(TextElement("一二三四 你好", Font(Typeface.makeFromName("微软雅黑", FontStyle.NORMAL), 18f)).apply {
        padding = Padding(0)
    })
    layout.add(TextElement("世界", Font(Typeface.makeFromName("微软雅黑", FontStyle.NORMAL), 18f)).apply {
        padding = Padding(0)
    })

    layout.add(
        ImageElement(loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\aaaa.jpg"))).apply {
            elementSize.mode = ElementSizeMode.SET_MODE
            elementSize.size = IntSize(100, 200)

            shadowInfo = ShadowInfo(offset = 0, sigma = 10)
            padding = Padding(20)
        }
    )

    layout.draw(layout.getCanvas(), DrawerContext(layout))
    layout.bitmap?.let { bitmap ->
        FileOutputStream(File("./test.png")).use {
            it.write(bitmap.toImage().encodeToData(EncodedImageFormat.PNG)!!.bytes)
        }
    }
}

