package com.theapp;

import android.support.annotation.NonNull;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface Cache<K, V> {
    @NonNull
    Maybe<V> get(@NonNull K k);

    @NonNull
    Completable put(@NonNull K k, @NonNull V v);
}
