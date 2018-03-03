package com.admin.theapp.utils.mappers

import com.admin.theapp.Hotel
import com.theapp.entities.HotelModel
import com.theapp.tools.Mapper

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotelToHotelModelMapper @Inject constructor() : Mapper<Hotel, HotelModel>() {

    override fun map(from: Hotel) =
            HotelModel(from.id, from.name, from.address, from.stars, from.distance,
                    from.imageName, from.suitesAvailability, from.lat, from.lon)
}
