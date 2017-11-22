package com.theapp.repository;

import com.admin.theapp.Hotel;

import io.reactivex.Maybe;

public class Repository {

    public Maybe<Hotel> getHotelById(long id) {
        final Hotel hotel = new Hotel(1, "Bellagio", "Las-Vegas", 5.0d, 1000d,
                                      "3.jpg", "55:77", 0, 0);
        return Maybe.just(hotel);
    }
}
