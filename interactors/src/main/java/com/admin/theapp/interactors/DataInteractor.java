package com.admin.theapp.interactors;

import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.theapp.repository.Repository;

import io.reactivex.Maybe;

public class DataInteractor {

    @NonNull
    private final Repository repository = new Repository();

    @NonNull
    public Maybe<Hotel> getHotelById(long id) {
        return repository.getHotelById(id);
    }

}
