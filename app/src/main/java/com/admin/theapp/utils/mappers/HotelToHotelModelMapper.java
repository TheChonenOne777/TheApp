package com.admin.theapp.utils.mappers;

import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.admin.theapp.model.HotelModel;
import com.theapp.tools.tools.Mapper;


public class HotelToHotelModelMapper extends Mapper<Hotel, HotelModel> {
    @NonNull
    @Override
    public HotelModel map(@NonNull Hotel hotel) {
        return new HotelModel(
                hotel.getId(),
                hotel.getName(),
                hotel.getAddress(),
                hotel.getStars(),
                hotel.getDistance(),
                hotel.getImageName(),
                hotel.getSuitesAvailability(),
                hotel.getLat(),
                hotel.getLon()
        );
    }
}
