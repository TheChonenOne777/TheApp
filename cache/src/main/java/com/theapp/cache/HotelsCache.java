package com.theapp.cache;

import android.support.annotation.NonNull;
import android.util.LruCache;

import com.admin.theapp.Hotel;
import com.theapp.Cache;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public class HotelsCache implements Cache<Long, Hotel> {

    @NonNull
    private final LruCache<Long, Hotel> cache;

    public HotelsCache(int size) {
        this.cache = new LruCache<>(size);
    }

    @NonNull
    @Override
    public Maybe<Hotel> get(@NonNull final Long key) {
        return Maybe.fromCallable(() -> cache.get(key));
    }

    @NonNull
    @Override
    public Completable put(@NonNull Long key, @NonNull Hotel val) {
        return Completable.fromAction(() -> cache.put(key, val));
    }
}
