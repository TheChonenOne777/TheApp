package com.theapp.repository;

import com.admin.theapp.Hotel;

import io.reactivex.Maybe;

public class HotelsRepo {

    public Maybe<Hotel> getHotelById(long id) {
        return Maybe.fromCallable(() -> new Hotel(1, "Bellagio", "Las-Vegas", 5.0d, 1000d,
                                      "3.jpg", "55:77", 0, 0));
    }
}
