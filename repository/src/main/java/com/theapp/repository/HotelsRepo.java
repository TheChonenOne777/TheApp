package com.theapp.repository;

import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.theapp.Cache;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Observable;

@Singleton
public class HotelsRepo {

    @NonNull
    private final Firebase           firebase;
    @NonNull
    private final Cache<Long, Hotel> hotelsCache;

    @Inject
    HotelsRepo(@NonNull Firebase firebase,
               @NonNull Cache<Long, Hotel> hotelsCache) {
        this.firebase = firebase;
        this.hotelsCache = hotelsCache;
    }

    @NonNull
    public Maybe<Hotel> getHotelById(long id) {
        return hotelsCache.get(id)
                          .switchIfEmpty(firebase.getHotelById(id)
                                                 .firstElement()
                                                 .doOnSuccess(hotel -> hotelsCache.put(id, hotel)));
    }

    @NonNull
    public Observable<List<Hotel>> getHotels() {
        return firebase.getHotels();
    }
}
