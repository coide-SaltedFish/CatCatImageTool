package org.sereinfish.catcat.image.tool.core.measure.size

import org.sereinfish.catcat.image.tool.core.element.Element

/**
 * 元素大小
 */
class ElementSize(
    var widthSize: SizeValue = SizeValue(ValueMode.WIDTH),
    var heightSize: SizeValue = SizeValue(ValueMode.HEIGHT),
) {
    /**
     * 获取大小
     */
    fun getSize(element: Element): IntSize{
        return IntSize(getWidth(element), getHeight(element))
    }

    data class SizeValue(
        var valueMode: ValueMode,
        var mode: SizeMode = SizeMode.ADAPTIVE,
        var value: Int = 10
    )

    fun getWidth(element: Element): Int {
        return when(widthSize.mode){
            SizeMode.MAX -> element.parent?.getSubElementMaxSize(element.parentIndex ?: 0, widthSize.valueMode) ?: kotlin.run {
                println("此元素没有找到父布局，无法使用最大化属性，将返回默认大小")
                element.elementSize.widthSize.value
            }
            SizeMode.ADAPTIVE -> element.getAdaptiveSize().width
            SizeMode.SET -> widthSize.value
        }
    }

    fun getHeight(element: Element): Int {
        return when(heightSize.mode){
            SizeMode.MAX -> element.parent?.getSubElementMaxSize(element.parentIndex ?: 0, heightSize.valueMode) ?: kotlin.run {
                println("此元素没有找到父布局，无法使用最大化属性，将返回默认大小")
                element.elementSize.heightSize.value
            }
            SizeMode.ADAPTIVE -> element.getAdaptiveSize().height
            SizeMode.SET -> heightSize.value
        }
    }

    enum class ValueMode{
        WIDTH, HEIGHT
    }
    enum class SizeMode{
        MAX, // 最大
        ADAPTIVE, // 自适应
        SET // 设置
    }
}