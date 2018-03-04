package com.admin.theapp.viewmodel

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.admin.theapp.Hotel
import com.admin.theapp.base.BaseViewModel
import com.admin.theapp.interactors.DataInteractor
import com.admin.theapp.utils.mappers.HotelToHotelModelMapper
import com.theapp.entities.HotelModel
import com.theapp.tools.Logger
import javax.inject.Inject

class HotelsViewModel @Inject constructor(
        logger: Logger,
        private val hotelMapper: HotelToHotelModelMapper,
        private val dataInteractor: DataInteractor
) : BaseViewModel(logger) {
    val hotels: MutableLiveData<List<HotelModel>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        dataInteractor.getHotels().execute(onSuccess = this::setHotels)
    }

    private fun setHotels(hotelList: List<Hotel>) {
        hotels.value = hotelMapper.map(hotelList)
    }
}