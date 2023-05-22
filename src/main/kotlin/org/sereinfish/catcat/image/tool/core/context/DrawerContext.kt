package org.sereinfish.catcat.image.tool.core.context

import org.jetbrains.skija.Canvas
import org.sereinfish.catcat.image.tool.core.element.Element

/**
 * 绘制上下文
 */
class DrawerContext(
    val element: Element, // 自己
    val parentElement: Element? = null, // 父组件
    val parentCanvas: Canvas? = null, // 父组件的画布
    override val context: HashMap<String, Any?> = HashMap(),
) : Context<String>