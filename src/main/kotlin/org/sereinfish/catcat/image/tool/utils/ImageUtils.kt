package org.sereinfish.catcat.image.tool.utils

import org.jetbrains.skija.Bitmap
import org.jetbrains.skija.Codec
import org.jetbrains.skija.Data
import org.jetbrains.skija.Image
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream

fun Bitmap.toImage() =
    Image.makeFromBitmap(this)

fun Image.toBitmap() =
    Bitmap.makeFromImage(this)

fun loadDataByFile(file: File) =
    Data.makeFromBytes(
        FileInputStream(file).use {
            BufferedInputStream(it).use {
                it.readAllBytes()
            }
        }
    )

fun loadBitmapByFile(
    file: File
) = Codec.makeFromData(loadDataByFile(file)).readPixels()

fun loadImageByFile(file: File) =
    loadBitmapByFile(file).toImage()