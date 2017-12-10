package com.admin.theapp.interactors;

import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.theapp.repository.HotelsRepo;

import io.reactivex.Maybe;

public class DataInteractor {

    @NonNull
    private final HotelsRepo hotelsRepo = new HotelsRepo();

    @NonNull
    public Maybe<Hotel> getHotelById(long id) {
        return hotelsRepo.getHotelById(id);
    }

}
