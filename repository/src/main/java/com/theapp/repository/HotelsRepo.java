package com.theapp.repository;

import com.admin.theapp.Hotel;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;

@Singleton
public class HotelsRepo {

    @Inject
    HotelsRepo() { // for dagger2
    }

    public Maybe<Hotel> getHotelById(long id) {
        return Maybe.fromCallable(() -> new Hotel(1, "Bellagio", "Las-Vegas", 5.0d, 1000d,
                                                  "3.jpg", "55:77", 0, 0
        ));
    }
}
