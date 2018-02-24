package com.theapp.cache

import android.util.LruCache

import com.admin.theapp.Hotel
import com.theapp.Cache

import io.reactivex.Completable
import io.reactivex.Maybe

class HotelsCache(lruCache: LruCache<Long, Hotel>) : Cache<Long, Hotel> {

    private val cache: LruCache<Long, Hotel> = lruCache

    override operator fun get(k: Long) = Maybe.fromCallable { cache.get(k) }

    override fun put(k: Long, v: Hotel) = Completable.fromAction { cache.put(k, v) }
}
