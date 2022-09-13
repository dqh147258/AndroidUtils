package com.yxf.androidutilsample.io

import com.yxf.androidutil.io.DataSerializable
import com.yxf.androidutil.io.DataSerializableCreator
import com.yxf.androidutil.io.readFloatArray
import com.yxf.androidutil.io.readList
import com.yxf.androidutil.io.writeFloatArray
import com.yxf.androidutil.io.writeList
import java.io.DataInputStream
import java.io.DataOutputStream

data class TestData(

    val test1: Float,
    val testArray: FloatArray,
    val testList: List<String>
) : DataSerializable {

    companion object CREATOR : DataSerializableCreator<TestData> {

        override fun readFromData(ins: DataInputStream): TestData {
            return TestData(ins.readFloat(), ins.readFloatArray(), ins.readList { it.readUTF() })
        }
    }

    override fun writeToData(out: DataOutputStream) {
        out.writeFloat(test1)
        out.writeFloatArray(testArray)
        out.writeList(testList) {it, t ->
            it.writeUTF(t)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TestData

        if (test1 != other.test1) return false
        if (!testArray.contentEquals(other.testArray)) return false
        if (testList != other.testList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = test1.hashCode()
        result = 31 * result + testArray.contentHashCode()
        result = 31 * result + testList.hashCode()
        return result
    }
}
