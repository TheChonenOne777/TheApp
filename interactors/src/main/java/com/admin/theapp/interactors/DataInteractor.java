package com.admin.theapp.interactors;

import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.theapp.repository.HotelsRepo;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;

@Singleton
public class DataInteractor {

    @Inject
    HotelsRepo hotelsRepo;

    @Inject
    DataInteractor() { // for dagger2
    }

    @NonNull
    public Maybe<Hotel> getHotelById(long id) {
        return hotelsRepo.getHotelById(id);
    }

}
