package com.admin.theapp.utils.mappers;

import android.support.annotation.NonNull;

import com.admin.theapp.model.FirebaseHotelModel;
import com.admin.theapp.model.HotelModel;
import com.theapp.tools.tools.Mapper;

public class FirebaseHotelModelToHotelModelMapper extends Mapper<FirebaseHotelModel, HotelModel> {
    @NonNull
    @Override
    public HotelModel map(@NonNull FirebaseHotelModel firebaseHotelModel) {
        return new HotelModel(
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
