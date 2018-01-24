package com.admin.theapp.interactors;

import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.theapp.repository.HotelsRepo;

import java.util.List;

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

    @NonNull
    public Maybe<List<Hotel>> getHotels() {
        return hotelsRepo.getHotels();
    }

    @NonNull
    public Maybe<byte[]> getBytes(@NonNull String name){
        return hotelsRepo.getBytes(name);
    }
}
