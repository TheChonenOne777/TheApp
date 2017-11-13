package com.admin.theapp.model;

import android.support.annotation.NonNull;

public class FirebaseHotelModelToHotelModelMapper extends Mapper<FirebaseHotelModel, HotelModel> {
    @NonNull
    @Override
    public HotelModel map(@NonNull FirebaseHotelModel firebaseHotelModel) {
        final HotelModel hotelModel = new HotelModel();
        hotelModel.setId(firebaseHotelModel.getId());
        hotelModel.setName(firebaseHotelModel.getName());
        hotelModel.setAddress(firebaseHotelModel.getAddress());
        hotelModel.setDistance(firebaseHotelModel.getDistance());
        hotelModel.setStars(firebaseHotelModel.getStars());
        hotelModel.setSuitesAvailability(firebaseHotelModel.getSuites_availability());
        hotelModel.setImageName(firebaseHotelModel.getImage());
        hotelModel.setLat(firebaseHotelModel.getLat());
        hotelModel.setLon(firebaseHotelModel.getLon());
        return hotelModel;
    }
}
