package org.sereinfish.catcat.image.tool.element

import org.jetbrains.skija.Font
import org.jetbrains.skija.Paint
import org.sereinfish.catcat.image.tool.builder.drawer.buildDrawer
import org.sereinfish.catcat.image.tool.core.element.AbstractElement
import org.sereinfish.catcat.image.tool.core.measure.Alignment
import org.sereinfish.catcat.image.tool.core.measure.AlignmentLayout
import org.sereinfish.catcat.image.tool.core.measure.offset.FloatOffset
import org.sereinfish.catcat.image.tool.core.measure.size.IntSize
import org.sereinfish.catcat.image.tool.utils.paint
import kotlin.math.roundToInt

class TextElement(
    var text: String,
    var font: Font,
    var letterSpacing: Int = 0, // 字间距
    var paint: Paint = paint(),
    override var alignment: Alignment = Alignment.LEFT
): AbstractElement(), AlignmentLayout {
    private var maxBottom = font.getBounds(font.getStringGlyphs(text)).maxOf { it.bottom }

    init {
        drawChain.add(buildDrawer { canvas, _ ->
            maxBottom = font.getBounds(font.getStringGlyphs(text)).maxOf { it.bottom }

            for (i in text.indices){
                val offset = getCharOffset(i)
                canvas.drawString("${text[i]}", offset.x,  offset.y, font, paint)
            }
        })
    }

    /**
     * 得到文字的位置
     */
    fun getCharOffset(index: Int): FloatOffset {
        return getAlignmentOffset(index)
    }

    /**
     * 文字占用布局大小
     */
    override fun getAdaptiveSize(): IntSize {
        return IntSize(getTextWidth().roundToInt(), getTextHeight().roundToInt())
    }

    fun getTextWidth(): Float {
        return font.getWidths(font.getStringGlyphs(text)).sum() + (text.length - 1) * letterSpacing
    }

    fun getTextHeight(): Float =
        font.getBounds(font.getStringGlyphs(text)).maxOf {
            it.height + it.bottom
        }

    override fun getAlignmentOffset(index: Int): FloatOffset {
        val baseOffset = getAlignmentOffset(getSize(), getAdaptiveSize()) // 获取基本对齐坐标
        val charX = padding.left + font.getWidths(font.getStringGlyphs(text.substring(0, index))).sum() + (index * letterSpacing)
        val charY = padding.top + getTextHeight() - maxBottom

        return baseOffset.apply {
            x += charX
            y += charY
        }
    }
}