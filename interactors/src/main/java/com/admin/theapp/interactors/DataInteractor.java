package com.admin.theapp.interactors;

import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.theapp.repository.HotelsRepo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class DataInteractor {

    @Inject
    HotelsRepo hotelsRepo;

    @Inject
    DataInteractor() { // for dagger2
    }

    @NonNull
    public Observable<Hotel> getHotelById(long id) {
        return hotelsRepo.getHotelById(id);
    }

    @NonNull
    public Observable<List<Hotel>> getHotels() {
        return hotelsRepo.getHotels();
    }
}
