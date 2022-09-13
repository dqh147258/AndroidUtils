package com.yxf.androidutil.io

import java.io.DataInputStream
import java.io.DataOutputStream

fun DataOutputStream.writeFloatArray(floatArray: FloatArray) {
    writeInt(floatArray.size)
    for (i in floatArray.indices) {
        writeFloat(floatArray[i])
    }
}

fun DataInputStream.readFloatArray(): FloatArray {
    val size = readInt()
    val floatArray = FloatArray(size)
    for (i in floatArray.indices) {
        floatArray[i] = readFloat()
    }
    return floatArray
}

fun DataOutputStream.writeDoubleArray(doubleArray: DoubleArray) {
    writeInt(doubleArray.size)
    for (i in doubleArray.indices) {
        writeDouble(doubleArray[i])
    }
}

fun DataInputStream.readDoubleArray(): DoubleArray {
    val size = readInt()
    val doubleArray = DoubleArray(size)
    for (i in doubleArray.indices) {
        doubleArray[i] = readDouble()
    }
    return doubleArray
}

fun DataOutputStream.writeIntArray(intArray: IntArray) {
    writeInt(intArray.size)
    for (i in intArray.indices) {
        writeInt(intArray[i])
    }
}

fun DataInputStream.readIntArray(): IntArray {
    val size = readInt()
    val intArray = IntArray(size)
    for (i in intArray.indices) {
        intArray[i] = readInt()
    }
    return intArray
}

fun DataOutputStream.writeLongArray(longArray: LongArray) {
    writeInt(longArray.size)
    for (i in longArray.indices) {
        writeLong(longArray[i])
    }
}

fun DataInputStream.readLongArray(): LongArray {
    val size = readInt()
    val longArray = LongArray(size)
    for (i in longArray.indices) {
        longArray[i] = readLong()
    }
    return longArray
}

fun DataOutputStream.writeBooleanArray(booleanArray: BooleanArray) {
    writeInt(booleanArray.size)
    for (i in booleanArray.indices) {
        writeBoolean(booleanArray[i])
    }
}

fun DataInputStream.readBooleanArray(): BooleanArray {
    val size = readInt()
    val booleanArray = BooleanArray(size)
    for (i in booleanArray.indices) {
        booleanArray[i] = readBoolean()
    }
    return booleanArray
}

fun DataOutputStream.writeCharArray(charArray: CharArray) {
    writeInt(charArray.size)
    for (i in charArray.indices) {
        writeChar(charArray[i].code)
    }
}

fun DataInputStream.readCharArray(): CharArray {
    val size = readInt()
    val charArray = CharArray(size)
    for (i in charArray.indices) {
        charArray[i] = readChar()
    }
    return charArray
}

fun DataOutputStream.writeShortArray(shortArray: ShortArray) {
    writeInt(shortArray.size)
    for (i in shortArray.indices) {
        writeShort(shortArray[i].toInt())
    }
}

fun DataInputStream.readShortArray(): ShortArray {
    val size = readInt()
    val shortArray = ShortArray(size)
    for (i in shortArray.indices) {
        shortArray[i] = readShort()
    }
    return shortArray
}


fun <T> DataOutputStream.writeArray(array: Array<T>, block: (out: DataOutputStream, t: T) -> Unit) {
    writeInt(array.size)
    for (i in array.indices) {
        block(this, array[i])
    }
}

inline fun <reified T> DataInputStream.readArray(block: (ins: DataInputStream) -> T): Array<T> {
    val size = readInt()
    val list = ArrayList<T>()
    for (i in 0 until size) {
        list.add(block(this))
    }
    return list.toTypedArray()
}

fun <T> DataOutputStream.writeList(list: List<T>, block: (out: DataOutputStream, t: T) -> Unit) {
    writeInt(list.size)
    for (i in list.indices) {
        block(this, list[i])
    }
}

fun <T> DataInputStream.readList(block: (ins: DataInputStream) -> T): List<T> {
    val size = readInt()
    val list = ArrayList<T>()
    for (i in 0 until size) {
        list.add(block(this))
    }
    return list
}
