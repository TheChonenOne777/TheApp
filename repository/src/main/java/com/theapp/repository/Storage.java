package com.theapp.repository;

import android.support.annotation.NonNull;

import io.reactivex.Maybe;

public interface Storage {
    @NonNull
    Maybe<byte[]> getBytes(@NonNull String name);
}
