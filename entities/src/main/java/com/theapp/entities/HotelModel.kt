package com.theapp.entities

data class HotelModel(val id: Long,
                 val name: String,
                 val address: String,
                 val stars: Double,
                 val distance: Double,
                 val imageName: String?,
                 val suitesAvailability: String?,
                 val lat: Double,
                 val lon: Double)
