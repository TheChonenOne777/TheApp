package com.theapp

import io.reactivex.Completable
import io.reactivex.Maybe

interface Cache<K, V> {
    fun get(k: K): Maybe<V>

    fun put(k: K, v: V): Completable
}
