package com.yxf.androidutilsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import java.io.BufferedOutputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileReader
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parcelTest()
    }

    private fun parcelTest() {
        val parcel = Parcel.obtain()
        try {
            parcel.writeString("parcel test")
            val bytes = parcel.marshall()
            val result = String(bytes)
            Log.d("Debug.parcel", "marshall value: $result")
            //D/Debug.parcel: marshall value: ������p��a��r��c��e��l�� ��t��e��s��t������
            val bf = ByteBuffer.allocate(1000)
            val fr = FileReader("")
        } finally {
            parcel.recycle()
        }
    }
}