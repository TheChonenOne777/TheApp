package com.admin.theapp.interactors

import com.theapp.repository.HotelsRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataInteractor @Inject constructor() {
    @Inject
    lateinit var hotelsRepo: HotelsRepo

    fun getHotels() = hotelsRepo.getHotels()

    fun getHotelById(id: Long) = hotelsRepo.getHotelById(id)

    fun getBytes(name: String) = hotelsRepo.getBytes(name)
}
