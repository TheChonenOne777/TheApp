package com.admin.theapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import com.admin.theapp.base.BaseViewModel
import com.admin.theapp.interactors.DataInteractor
import com.admin.theapp.utils.Decoder
import com.admin.theapp.utils.mappers.HotelToHotelModelMapper
import com.theapp.entities.HotelModel
import com.theapp.tools.Logger
import javax.inject.Inject


class HotelDetailsViewModel @Inject constructor(
        logger: Logger,
        private val dataInteractor: DataInteractor,
        private val decoder: Decoder,
        private val hotelsMapper: HotelToHotelModelMapper
) : BaseViewModel(logger) {

    val hotelModel = MutableLiveData<HotelModel>()
    val hotelImage = MutableLiveData<BitmapDrawable>()
    val mapImage = MutableLiveData<Bitmap>()

    fun retrieveHotelModel(id: Long) {
        dataInteractor.getHotelById(id).execute(onSuccess = { hotelModel.value = hotelsMapper.map(it) })
    }

    fun getImageByName(name: String) {
        dataInteractor.getImageBytes(name).execute(onSuccess = { hotelImage.value = decoder.decode(it) })
    }

    fun getMapThumbnail(lat: Double, lon: Double) {
        dataInteractor.getImageStream(
                "http://maps.google.com/maps/api/staticmap?center=${lat},${lon}" +
                        "&zoom=16" +
                        "&size=200x200" +
                        "&sensor=true"
        ).execute(onSuccess = { mapImage.value = decoder.decode(it) })
    }
}