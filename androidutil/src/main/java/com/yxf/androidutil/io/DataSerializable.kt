package com.yxf.androidutil.io

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import kotlin.reflect.KClass


interface DataSerializable {

    fun writeToData(out: DataOutputStream)

}

interface DataSerializableCreator<T> {
    fun readFromData(ins: DataInputStream): T
}

fun DataSerializable.toFile(path: String): File {
    val file = File(path)
    val out = DataOutputStream(file.outputStream())
    out.use {
        writeToData(it)
    }
    return file
}

fun <T> DataSerializableCreator<T>.fromFile(path: String): T {
    val file = File(path)
    val ins = DataInputStream(file.inputStream())
    ins.use {
        return readFromData(it)
    }
}

