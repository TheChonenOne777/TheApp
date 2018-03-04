package com.admin.theapp.logger

import android.util.Log

import com.theapp.tools.Logger

class AppLogger : Logger {

    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    override fun e(tag: String, msg: String) {
        Log.e(tag, msg)
    }

    override fun e(tag: String, msg: String, tr: Throwable) {
        Log.e(tag, msg, tr)
    }

    override fun i(tag: String, msg: String) {
        Log.i(tag, msg)
    }

    override fun v(tag: String, msg: String) {
        Log.v(tag, msg)
    }

    override fun w(tag: String, msg: String) {
        Log.w(tag, msg)
    }
}
