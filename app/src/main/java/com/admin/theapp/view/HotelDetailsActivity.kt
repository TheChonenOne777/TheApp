package com.admin.theapp.view

import android.arch.lifecycle.Observer
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import com.admin.theapp.R
import com.admin.theapp.base.BaseActivityNew
import com.admin.theapp.utils.Constants
import com.admin.theapp.utils.toast
import com.admin.theapp.viewmodel.HotelDetailsViewModel
import com.theapp.entities.HotelModel
import kotlinx.android.synthetic.main.hotel_details.*

class HotelDetailsActivity : BaseActivityNew<HotelDetailsViewModel>() {
    override val vmClass = HotelDetailsViewModel::class.java
    override val layoutRes = R.layout.hotel_details

    private val hotelModelObserver = Observer<HotelModel>({
        it ?: return@Observer

        hotel_details_name.text = it.name
        hotel_details_address.text = it.address
        hotel_details_stars.setStars(it.stars)
        hotel_details_distance.text = it.distance.toString()
        hotel_details_suites_availability.text = it.suitesAvailability
        hotel_details_lat.text = it.lat.toString()
        hotel_details_lon.text = it.lon.toString()
        it.imageName?.let { viewModel.getImageByName(it) }
    })

    private val hotelImageObserver = Observer<BitmapDrawable>({ hotel_details_image.setImageDrawable(it) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val hotelId = intent.getLongExtra(Constants.EXTRA_HOTEL_DETAILS_ACTIVITY, DEFAULT_ACTIVITY_RESULT_HOTEL_ID)
        if (hotelId == DEFAULT_ACTIVITY_RESULT_HOTEL_ID) {
            toast(R.string.hotel_does_not_exist_text)
            finish()
        }
        viewModel.retrieveHotelModel(hotelId)
        viewModel.hotelModel.observe(this, hotelModelObserver)
        viewModel.hotelImage.observe(this, hotelImageObserver)
    }

    private companion object {
        const val DEFAULT_ACTIVITY_RESULT_HOTEL_ID: Long = -1
    }
}
