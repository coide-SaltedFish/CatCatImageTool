package org.sereinfish.catcat.image.tool.core.measure.size

import org.sereinfish.catcat.image.tool.core.element.Element

/**
 * 元素大小获取
 */
class ElementSize {
    var size: IntSize = IntSize(10, 10)
    var mode: ElementSizeMode = ElementSizeMode.DEFINED_MODE

    /**
     * 获取元素的大小
     */
    fun getSize(element: Element): IntSize{
        return when(mode){
            ElementSizeMode.FILL_MODE -> {
                element.parent?.getSize() ?: kotlin.run {
                    println("似乎此元素已是顶层元素，在此情况下 FILL_MODE 模式无法使用，将按照 SET_MODE 模式返回此元素大小")
                    size
                }
            } // 占满空间，大小委托给父类
            ElementSizeMode.FLEXIBLE_MODE -> {
                // 自适应模式，大小根据子元素大小确定
                IntSize().also { size ->
                    element.subElements.forEach { element ->
                        val subSize = element.getLayoutSize()
                        size.width = maxOf(size.width, subSize.width)
                        size.height = maxOf(size.height, subSize.height)
                    }
                }
            }
            ElementSizeMode.SET_MODE -> size // 自定义大小模式
            ElementSizeMode.DEFINED_MODE -> element.getSize()
        }
    }


}

enum class ElementSizeMode{
    FILL_MODE, // 占满模式
    FLEXIBLE_MODE, // 自适应大小模式
    SET_MODE, // 自定义模式
    DEFINED_MODE // 默认模式
}