package com.admin.theapp.utils

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import java.io.InputStream

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Decoder @Inject constructor(private val context: Context) {

    fun decode(data: ByteArray) =
            BitmapDrawable(context.resources, BitmapFactory.decodeByteArray(data, 0, data.size))

    fun decode(data: InputStream) =
            BitmapFactory.decodeStream(data)
}
