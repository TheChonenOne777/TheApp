package com.theapp.repository;

import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;


public interface Firebase {
    @NonNull
    Observable<List<Hotel>> getHotels();

    @NonNull
    Maybe<Hotel> getHotelById(long id);
}
