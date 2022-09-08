package com.yxf.androidutil.ktx

import android.os.Parcelable
import com.yxf.androidutil.file.ParcelEditor
import java.io.File

fun <T : Parcelable> T.toByteArray(): ByteArray {
    return ParcelEditor<T>().toByteArray(this)
}

fun <T : Parcelable> T.toFile(path: String): File {
    return ParcelEditor<T>().toFile(this, path)
}

fun <T : Parcelable> Parcelable.Creator<T>.fromByteArray(byteArray: ByteArray): T {
    return ParcelEditor<T>().fromByteArray(byteArray, this)
}

fun <T : Parcelable> Parcelable.Creator<T>.fromFile(path: String): T {
    return ParcelEditor<T>().fromFile(path, this)
}