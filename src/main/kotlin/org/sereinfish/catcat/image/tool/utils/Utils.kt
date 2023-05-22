package org.sereinfish.catcat.image.tool.utils

import org.jetbrains.skija.Color
import org.jetbrains.skija.Paint

fun Any?.println() =
    println(this)

fun Boolean?.isTrue() =
    this == true

fun Boolean?.isFalse() =
    this == false

fun Any?.isNull() = this == null

fun <T> List<T>.subList(start: Int) =
    this.subList(start, this.size)

inline fun Boolean.isTrue(block: () -> Unit): Boolean{
    if (this) block.invoke()
    return this
}

inline fun Boolean.isFalse(block: () -> Unit): Boolean{
    if (this.not()) block.invoke()
    return this
}

fun paint(block: Paint.() -> Unit = {}): Paint{
    return Paint().apply {
        isAntiAlias = true
    }.apply(block)
}

fun Int.getColorARGB() =
    Color.makeARGB(
        Color.getA(this),
        Color.getR(this),
        Color.getG(this),
        Color.getB(this)
    )

/**
 * 循环遍历检查
 * action返回 false 停止检查，并且返回 false
 * 默认返回 true
 */
inline fun <T> Iterable<T>.forEachCheck(action: (T) -> Boolean): Boolean {
    for (element in this){
        if(action(element).not()){
            return false
        }
    }
    return true
}

inline fun <T> Array<out T>.forEachCheck(action: (T) -> Boolean): Boolean {
    for (element in this){
        if(action(element).not()){
            return false
        }
    }
    return true
}

/**
 * 循环遍历检查，不会中途退出
 * 默认返回 true, action 中有 false 则返回 false
 */
inline fun <T> Iterable<T>.forEachResult(action: (T) -> Boolean): Boolean {
    var ret = true
    for (element in this){
        if(action(element).not()){
            ret = false
        }
    }
    return ret
}

inline fun <T> Array<out T>.forEachResult(action: (T) -> Boolean): Boolean {
    var ret = true
    for (element in this){
        if(action(element).not()){
            ret = false
        }
    }
    return ret
}