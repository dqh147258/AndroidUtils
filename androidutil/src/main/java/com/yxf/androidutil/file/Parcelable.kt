package com.yxf.androidutil.file

import android.os.Parcelable
import java.io.File

@Deprecated("unsafety")
fun <T : Parcelable> T.toByteArray(): ByteArray {
    return ParcelEditor<T>().toByteArray(this)
}

@Deprecated("unsafety")
fun <T : Parcelable> T.toFile(path: String): File {
    return ParcelEditor<T>().toFile(this, path)
}

@Deprecated("unsafety")
fun <T : Parcelable> Parcelable.Creator<T>.fromByteArray(byteArray: ByteArray): T {
    return ParcelEditor<T>().fromByteArray(byteArray, this)
}

@Deprecated("unsafety")
fun <T : Parcelable> Parcelable.Creator<T>.fromFile(path: String): T {
    return ParcelEditor<T>().fromFile(path, this)
}