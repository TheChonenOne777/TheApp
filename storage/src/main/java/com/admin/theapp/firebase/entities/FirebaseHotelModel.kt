package com.admin.theapp.firebase.entities

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class FirebaseHotelModel(val id: Long = 0L,
                         val name: String = "",
                         val address: String = "",
                         val stars: Double = 0.0,
                         val distance: Double = 0.0,
                         val suites_availability: String = "",
                         val lat: Double = 0.0,
                         val lon: Double = 0.0,
                         val image: String = "")
