package com.theapp.repository;

import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.theapp.Cache;
import com.theapp.tools.adapters.DisposableCompletableObserverAdapter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;

@Singleton
public class HotelsRepo {

    @NonNull
    private final Database           database;
    @NonNull
    private final Storage            storage;
    @NonNull
    private final Cache<Long, Hotel> hotelsCache;

    @Inject
    HotelsRepo(@NonNull Database database,
               @NonNull Cache<Long, Hotel> hotelsCache,
               @NonNull Storage storage) {
        this.database = database;
        this.hotelsCache = hotelsCache;
        this.storage = storage;
    }

    @NonNull
    public Maybe<Hotel> getHotelById(long id) {
        return hotelsCache.get(id)
                          .switchIfEmpty(database.getHotelById(id)
                                                 .doOnSuccess(hotel -> hotelsCache.put(id, hotel).subscribe(new DisposableCompletableObserverAdapter() {})));
    }

    @NonNull
    public Maybe<List<Hotel>> getHotels() {
        return database.getHotels();
    }

    public Maybe<byte[]> getBytes(@NonNull String name) {
        return storage.getBytes(name);
    }
}
