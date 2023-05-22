package org.sereinfish.catcat.image.tool.element

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.draw.Drawer
import org.sereinfish.catcat.image.tool.utils.loadImageByFile
import java.io.File


class TestElement: AbstractElement() {
    val image = loadImageByFile(File("C:\\Users\\MI\\Desktop\\temp\\aaaa.jpg"))

    init {
        drawerChain.add(object : Drawer{
            override fun draw(canvas: Canvas, context: DrawerContext) {
                canvas.translate(20f, 20f)
                drawShadowsBlurs(canvas)
                drawImageFilters(canvas, 2000f, 1f);
                drawLights(canvas);
            }
        })
    }

    private fun drawShadowsBlurs(canvas: Canvas) {
        canvas.save()
        Paint().setColor(-0x717937).use { fill ->
            Path().use { path ->
                path.fillMode = PathFillMode.EVEN_ODD
                path.lineTo(0f, 60f).lineTo(60f, 60f).lineTo(60f, 0f).closePath()
                path.moveTo(10f, 5f).lineTo(55f, 10f).lineTo(50f, 55f).lineTo(5f, 50f).closePath()
                val filters = arrayOf(
                    ImageFilter.makeDropShadow(0f, 0f, 10f, 10f, -0x1000000),
                    ImageFilter.makeDropShadow(2f, 2f, 0f, 0f, -0x1000000),
                    ImageFilter.makeDropShadow(0f, 0f, 10f, 10f, -0xbdc8e),
                    ImageFilter.makeDropShadowOnly(0F, 0f, 2f, 2f, -0x33cccd),
                    ImageFilter.makeDropShadow(0f, 0f, 2f, 2f, -0x33cccd, null, IRect.makeXYWH(30, 30, 30, 30)),
                    ImageFilter.makeDropShadow(
                        2f,
                        2f,
                        2f,
                        2f,
                        -0xcccc34,
                        ImageFilter.makeDropShadow(-2f, -2f, 2f, 2f, -0x33cccd),
                        null
                    ),
                    ImageFilter.makeBlur(2f, 2f, FilterTileMode.CLAMP),
                    ImageFilter.makeBlur(2f, 2f, FilterTileMode.REPEAT),
                    ImageFilter.makeBlur(2f, 2f, FilterTileMode.MIRROR),
                    ImageFilter.makeBlur(2f, 2f, FilterTileMode.DECAL)
                )
                for (filter in filters) {
                    fill.imageFilter = filter
                    canvas.drawPath(path, fill)
                    canvas.translate(70f, 0f)
                    filter.close()
                }
            }
        }
        canvas.restore()
        canvas.translate(0f, 70f)
    }

    private fun drawImageFilters(canvas: Canvas, width: Float, dpi: Float) {
        canvas.save()
        Paint().setColor(-0x60e5).use { fill ->
            Path().use { path ->
                path.fillMode = PathFillMode.EVEN_ODD
                path.moveTo(10f, 10f).rMoveTo(20f, 1.6f).rLineTo(11.7f, 36.2f).rLineTo(-30.8f, -22.4f).rLineTo(38.1f, 0f)
                    .rLineTo(-30.8f, 22.4f)
                val bb = IRect.makeXYWH(0, 0, 60, 60)
                val filters = arrayOf(
                    ImageFilter.makeOffset(0f, 0f, null, bb),
                    ImageFilter.makeMagnifier(Rect.makeXYWH(0 * dpi, 0 * dpi, 60 * dpi, 60 * dpi), 5f, null, bb),
                    ImageFilter.makeMagnifier(Rect.makeXYWH(0 * dpi, 0 * dpi, 60 * dpi, 60 * dpi), 10f, null, bb),
                    ImageFilter.makeMagnifier(Rect.makeXYWH(0 * dpi, 0 * dpi, 60 * dpi, 60 * dpi), 20f, null, bb),
                    ImageFilter.makeOffset(10f, 10f, null, bb),
                    ImageFilter.makePaint(fill, bb),
                    ImageFilter.makeTile(Rect.makeXYWH(10f, 10f, 40f, 40f), Rect.makeXYWH(0f, 0f, 60f, 60f), null),
                    ImageFilter.makeDilate(2f, 2f, null, bb),
                    ImageFilter.makeErode(2f, 2f, null, bb),
                    ImageFilter.makeColorFilter(
                        ColorFilter.makeBlend(-0x7fffff01, BlendMode.SRC_OVER),
                        ImageFilter.makeDropShadow(0f, 0f, 10f, 10f, -0x1000000),
                        bb
                    ),
                    ImageFilter.makeImage(
                        image,
                        Rect.makeXYWH(200f, 200f, 200f, 200f),
                        Rect.makeXYWH(10f, 10f, 40f, 40f),
                        CubicResampler.MITCHELL
                    )
                )
                for (filter in filters) {
                    fill.imageFilter = filter
                    canvas.drawPath(path, fill)
                    canvas.translate(70f, 0f)
                    filter.close()
                }
            }
        }
        canvas.restore()
        canvas.translate(0f, 70f)
    }

    private fun drawLights(canvas: Canvas) {
        canvas.save()
        Paint().setColor(-0x60e5).use { fill ->
            Path().use { path ->
                path.fillMode = PathFillMode.EVEN_ODD
                path.moveTo(10f, 10f).rMoveTo(20f, 1.6f).rLineTo(11.7f, 36.2f).rLineTo(-30.8f, -22.4f).rLineTo(38.1f, 0f)
                    .rLineTo(-30.8f, 22.4f)
                val bb = IRect.makeXYWH(0, 0, 60, 60)
                val filters = arrayOf(
                    ImageFilter.makeDistantLitDiffuse(0f, 1f, 1f, -0x60e5, 1f, 0.5f, null, bb),
                    ImageFilter.makeDistantLitDiffuse(0f, -1f, 1f, -0x60e5, 1f, 0.5f, null, bb),
                    ImageFilter.makeDistantLitDiffuse(1f, 0f, 1f, -0x60e5, 1f, 0.5f, null, bb),
                    ImageFilter.makeDistantLitDiffuse(-1f, 0f, 1f, -0x60e5, 1f, 0.5f, null, bb),
                    ImageFilter.makeDistantLitDiffuse(-1f, -1f, 1f, -0x60e5, 1f, 0.5f, null, bb),
                    ImageFilter.makePointLitDiffuse(0f, 0f, 30f, -0x60e5, 1f, 0.5f, null, bb),
                    ImageFilter.makeSpotLitDiffuse(0f, 0f, 30f, 30f, 30f, 0f, 1f, 30f, -0x60e5, 1f, 0.5f, null, bb),
                    ImageFilter.makeDistantLitSpecular(-1f, -1f, 1f, -0x60e5, 1f, 1.1f, 1.1f, null, bb),
                    ImageFilter.makePointLitSpecular(0f, 0f, 30f, -0x60e5, 1f, 1.1f, 1.1f, null, bb),
                    ImageFilter.makeSpotLitSpecular(0f, 0f, 30f, 30f, 30f, 0f, 1f, 30f, -0x60e5, 1f, 1.1f, 1.1f, null, bb)
                )
                for (filter in filters) {
                    fill.imageFilter = filter
                    canvas.drawPath(path, fill)
                    canvas.translate(70f, 0f)
                    filter.close()
                }
            }
        }
        canvas.restore()
        canvas.translate(0f, 70f)
    }
}