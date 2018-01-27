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
    private final FirebaseDatabase   firebaseDatabase;
    @NonNull
    private final FirebaseStorage    firebaseStorage;
    @NonNull
    private final Cache<Long, Hotel> hotelsCache;

    @Inject
    HotelsRepo(@NonNull FirebaseDatabase firebaseDatabase,
               @NonNull Cache<Long, Hotel> hotelsCache,
               @NonNull FirebaseStorage firebaseStorage) {
        this.firebaseDatabase = firebaseDatabase;
        this.hotelsCache = hotelsCache;
        this.firebaseStorage = firebaseStorage;
    }

    @NonNull
    public Maybe<Hotel> getHotelById(long id) {
        return hotelsCache.get(id)
                          .switchIfEmpty(firebaseDatabase.getHotelById(id)
                                                         .doOnSuccess(hotel -> hotelsCache.put(id, hotel).subscribe(new DisposableCompletableObserverAdapter() {})));
    }

    @NonNull
    public Maybe<List<Hotel>> getHotels() {
        return firebaseDatabase.getHotels();
    }

    public Maybe<byte[]> getBytes(@NonNull String name) {
        return firebaseStorage.getBytes(name);
    }
}
