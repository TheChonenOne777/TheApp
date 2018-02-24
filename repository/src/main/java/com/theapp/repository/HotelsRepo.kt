package com.theapp.repository

import com.admin.theapp.Hotel
import com.theapp.Cache
import com.theapp.tools.adapters.DisposableCompletableObserverAdapter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotelsRepo @Inject internal constructor(
        private val database: Database,
        private val hotelsCache: Cache<Long, Hotel>,
        private val storage: Storage
) {

    fun getHotels() = database.getHotels()

    fun getHotelById(id: Long) =
            hotelsCache.get(id)
                    .switchIfEmpty(database.getHotelById(id)
                            .doOnSuccess { hotel ->
                                hotelsCache.put(id, hotel).subscribe(object : DisposableCompletableObserverAdapter() {})
                            })

    fun getBytes(name: String) = storage.getBytes(name)
}
