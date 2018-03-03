package com.admin.theapp.firebase.utils.mappers

import com.admin.theapp.Hotel
import com.admin.theapp.firebase.entities.FirebaseHotelModel
import com.theapp.tools.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseHotelModelToHotelMapper @Inject constructor() : Mapper<FirebaseHotelModel, Hotel>() {

    override fun map(from: FirebaseHotelModel) =
            Hotel(from.id, from.name, from.address, from.stars, from.distance,
                    from.image, from.suites_availability, from.lat, from.lon)
}
