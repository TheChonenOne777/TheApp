package com.admin.theapp.firebase.utils.mappers;

import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.admin.theapp.firebase.model.FirebaseHotelModel;
import com.theapp.tools.Mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FirebaseHotelModelToHotelMapper extends Mapper<FirebaseHotelModel, Hotel> {

    @Inject
    FirebaseHotelModelToHotelMapper() { // for dagger2
    }

    @NonNull
    @Override
    public Hotel map(@NonNull FirebaseHotelModel firebaseHotelModel) {
        return new Hotel(
                firebaseHotelModel.getId(),
                firebaseHotelModel.getName(),
                firebaseHotelModel.getAddress(),
                firebaseHotelModel.getStars(),
                firebaseHotelModel.getDistance(),
                firebaseHotelModel.getImage(),
                firebaseHotelModel.getSuites_availability(),
                firebaseHotelModel.getLat(),
                firebaseHotelModel.getLon()
        );
    }
}
