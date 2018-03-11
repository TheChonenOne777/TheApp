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
                "https://maps.googleapis.com/maps/api/staticmap?center=${lat},${lon}" +
                        "&zoom=15" +
                        "&size=1000x1000" +
                        "&maptype=roadmap" +
                        "&markers=color:red%7Clabel:A%7C${lat},${lon}" +
                        "&key=AIzaSyDpkQeM0ec2sQNxx-2S1xONWf6gQmk9Sc0"
        ).execute(onSuccess = { mapImage.value = decoder.decode(it) })
    }
}
