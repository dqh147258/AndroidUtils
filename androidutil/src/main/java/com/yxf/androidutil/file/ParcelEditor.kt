package com.yxf.androidutil.file

import android.os.Parcel
import android.os.Parcelable
import java.io.File

@Deprecated("unsafety")
class ParcelEditor<T : Parcelable>() {

    fun toByteArray(target: T): ByteArray {
        val parcel = Parcel.obtain()
        try {
            target.writeToParcel(parcel, 0)
            return parcel.marshall()
        } finally {
            parcel.recycle()
        }
    }

    fun fromByteArray(byteArray: ByteArray, creator: Parcelable.Creator<T>, offset: Int = 0, length: Int = byteArray.size): T {
        val parcel = Parcel.obtain()
        try {
            parcel.unmarshall(byteArray, offset, length)
            parcel.setDataPosition(0)
            return creator.createFromParcel(parcel)
        } finally {
            parcel.recycle()
        }
    }

    fun toFile(target: T, path: String): File {
        val file = File(path)
        if (file.exists()) {
            file.delete()
            file.createNewFile()
        }
        file.outputStream().use {
            it.write(toByteArray(target))
        }
        return file
    }

    fun fromFile(path: String, creator: Parcelable.Creator<T>): T {
        return fromByteArray((File(path).inputStream().use { it.readBytes() }), creator)
    }


}