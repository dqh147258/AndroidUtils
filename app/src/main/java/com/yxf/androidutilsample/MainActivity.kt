package com.yxf.androidutilsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import com.yxf.androidutil.coroutine.launch
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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
        //parcelTest()
        launchTest()
    }

    private fun launchTest() {
        GlobalScope.launch(Dispatchers.IO, CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("Debug.launch", "catch exception, current thread: ${Thread.currentThread().id}")
        }) {
            Log.d("Debug.launch", "launch, current thread: ${Thread.currentThread().id}")
            throw RuntimeException("Test exception")
        }
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