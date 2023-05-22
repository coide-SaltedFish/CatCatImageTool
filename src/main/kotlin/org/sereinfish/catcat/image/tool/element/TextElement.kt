package org.sereinfish.catcat.image.tool.element

import org.jetbrains.skija.*
import org.sereinfish.catcat.image.tool.builder.drawer.PaddingDrawer
import org.sereinfish.catcat.image.tool.core.context.DrawerContext
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.core.measure.Padding
import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset
import org.sereinfish.catcat.image.tool.core.measure.size.ElementSizeMode
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.core.shadow.ShadowInfo
import org.sereinfish.catcat.image.tool.draw.Drawer
import org.sereinfish.catcat.image.tool.utils.DrawerLevel
import org.sereinfish.catcat.image.tool.utils.paint
import kotlin.math.roundToInt

class TextElement(
    var text: String,
    var font: Font,
    var letterSpacing: Int = 0, // 字间距
    var textColor: Int = Color.makeRGB(0, 0, 0), // 文本颜色
    var textShader: Shader? = null, // 文本特效
    var textShadow: ShadowInfo? = null, // 文本阴影
    var textPadding: Padding = Padding(), // 文本内边距
    // 画笔设置
    var paintMode: PaintMode? = null,
    var strokeWidth: Float = 1f,
    var alignment: Alignment = Alignment.LEFT
): AbstractElement() {
    private val maxBottom = font.getBounds(font.getStringGlyphs(text)).maxOf { it.bottom }

    init {
        /**
         * 添加文本绘制器
         */
        drawerChain.add(object : Drawer{
            override fun draw(canvas: Canvas, context: DrawerContext) {
                canvas.restore()

                paint {
                    color = textColor
                    shader = textShader

                    textShadow?.let {
                        imageFilter = ImageFilter.makeDropShadow(
                            it.dx.toFloat(),
                            it.dy.toFloat(),
                            it.sigmaX.toFloat(),
                            it.sigmaY.toFloat(),
                            it.color
                        )
                    }
                    paintMode?.let {
                        mode = it
                    }
                    setBlendMode(BlendMode.SRC_OVER)
                    strokeWidth = this@TextElement.strokeWidth
                }.use { paint ->
                    for (i in text.indices){
                        val offset = getCharOffset(i)
                        canvas.drawString("${text[i]}", offset.x,  offset.y, font, paint)
                    }
                }

                PaddingDrawer().draw(canvas, context)
            }
        }, DrawerLevel.NORMAL)
    }

    /**
     * 得到文字的位置
     */
    fun getCharOffset(index: Int): FloatOffset{
        val offset = FloatOffset().apply {
            for (i in 0 until index){
                x += font.getWidths(font.getStringGlyphs("${text[i]}")).first() + letterSpacing
            }
            y = getTextHeight() - maxBottom
        }
        if (alignment == Alignment.CENTER){
            val size = getSize()
            val lw = (size.width - getTextWidth()) / 2
            val ly = (size.height - getTextHeight()) / 2
            offset.apply {
                x += lw
                y += ly
            }
        }
        return offset.apply {
            x += padding.left.toFloat() + textPadding.left.toFloat()
            y += padding.top.toFloat() + textPadding.right.toFloat()
        }
    }

    override fun getSize(): IntSize {
        return if (elementSize.mode == ElementSizeMode.DEFINED_MODE)
            IntSize(getTextWidth().roundToInt(), getTextHeight().roundToInt()).apply {
                add(textPadding.getSize())
            }
        else elementSize.getSize(this)
    }

    fun getTextWidth(): Float {
        return font.getWidths(font.getStringGlyphs(text)).sum() + (text.length - 1) * letterSpacing
    }

    fun getTextHeight(): Float =
        font.getBounds(font.getStringGlyphs(text)).maxOf {
            it.height + it.bottom
        }
}