package com.theapp.repository

import io.reactivex.Maybe

interface Storage {
    fun getBytes(name: String): Maybe<ByteArray>
}
