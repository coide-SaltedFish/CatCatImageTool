package org.sereinfish.catcat.image.tool.utils

import org.jetbrains.skija.*
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

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

/**
 * 写入图像到文件
 */
fun writeImageByFile(image: Image, file: File, encodedImageFormat: EncodedImageFormat = EncodedImageFormat.PNG): File{
    FileOutputStream(file).use {
        it.write(image.encodeToData(encodedImageFormat)!!.bytes)
    }
    return file
}
