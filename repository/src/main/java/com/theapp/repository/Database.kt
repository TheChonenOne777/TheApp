package com.theapp.repository

import com.admin.theapp.Hotel

import io.reactivex.Maybe

interface Database {
    fun getHotels(): Maybe<List<Hotel>>

    fun getHotelById(id: Long): Maybe<Hotel>
}
