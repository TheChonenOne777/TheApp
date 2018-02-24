package com.admin.theapp.firebase.utils.mappers

import com.admin.theapp.Hotel
import com.theapp.entities.FirebaseHotelModel
import com.theapp.tools.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseHotelModelToHotelMapper @Inject internal constructor() : Mapper<FirebaseHotelModel, Hotel>() {

    override fun map(firebaseHotelModel: FirebaseHotelModel): Hotel {
        return Hotel(
                firebaseHotelModel.id,
                firebaseHotelModel.name,
                firebaseHotelModel.address,
                firebaseHotelModel.stars,
                firebaseHotelModel.distance,
                firebaseHotelModel.image,
                firebaseHotelModel.suites_availability,
                firebaseHotelModel.lat,
                firebaseHotelModel.lon
        )
    }
}
