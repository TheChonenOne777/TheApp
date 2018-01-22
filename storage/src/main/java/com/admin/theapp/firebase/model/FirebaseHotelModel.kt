package com.admin.theapp.firebase.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class FirebaseHotelModel {
    val id: Long
    val name: String
    val address: String
    val stars: Double
    val distance: Double
    val suites_availability: String
    val lat: Double
    val lon: Double
    val image: String

    constructor() {
        id = 0
        name = ""
        address = ""
        stars = 0.0
        distance = 0.0
        suites_availability = ""
        lat = 0.0
        lon = 0.0
        image = ""
    }

    constructor(id: Long, name: String, address: String, stars: Double, distance: Double, suites_availability: String, lat: Double, lon: Double, image: String) {
        this.id = id
        this.name = name
        this.address = address
        this.stars = stars
        this.distance = distance
        this.suites_availability = suites_availability
        this.lat = lat
        this.lon = lon
        this.image = image
    }
}
