package com.theapp.repository;

import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Observable;

@Singleton
public class HotelsRepo {

    @NonNull
    private final Firebase firebase;

    @Inject
    HotelsRepo(@NonNull Firebase firebase) {
        this.firebase = firebase;
    }

    @NonNull
    public Maybe<Hotel> getHotelById(long id) {
        return Maybe.fromCallable(() -> new Hotel(1, "Bellagio", "Las-Vegas", 5.0d, 1000d,
                                                  "3.jpg", "55:77", 0, 0
        ));
    }

    @NonNull
    public Observable<List<Hotel>> getHotels() {
        return firebase.getHotels();
    }
}
