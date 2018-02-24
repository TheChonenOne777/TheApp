package com.theapp.tools

interface Logger {

    fun d(tag: String, msg: String)

    fun e(tag: String, msg: String)

    fun e(tag: String, msg: String, tr: Throwable)

    fun i(tag: String, msg: String)

    fun v(tag: String, msg: String)

    fun w(tag: String, msg: String)

}
