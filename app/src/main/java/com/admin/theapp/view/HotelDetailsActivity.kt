package com.admin.theapp.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import com.admin.theapp.R
import com.admin.theapp.base.BaseActivity
import com.admin.theapp.utils.Constants
import com.admin.theapp.utils.toast
import com.admin.theapp.viewmodel.HotelDetailsViewModel
import com.theapp.entities.HotelModel
import kotlinx.android.synthetic.main.hotel_details.*

class HotelDetailsActivity : BaseActivity<HotelDetailsViewModel>() {
    override val vmClass = HotelDetailsViewModel::class.java
    override val layoutRes = R.layout.hotel_details

    private val hotelModelObserver = Observer<HotelModel>({
        it ?: return@Observer

        hotel_details_name.text = it.name
        hotel_details_address.text = it.address
        hotel_details_stars.setStars(it.stars)
        hotel_details_distance.text = it.distance.toString()
        hotel_details_suites_availability.text = it.suitesAvailability
        it.imageName?.let { viewModel.getImageByName(it) }
        viewModel.getMapThumbnail(it.lat, it.lon)
        hotel_details_on_map_button.setOnClickListener { _ ->
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(
                    R.string.map_query,
                    it.lat.toString(),
                    it.lon.toString(),
                    it.name.replace(' ', '+')
            )))
            if (intent.resolveActivity(packageManager) != null) startActivity(intent)
        }
    })

    private val hotelImageObserver = Observer<BitmapDrawable>({ hotel_details_image.setImageDrawable(it) })

    private val mapImageObserver = Observer<Bitmap>({ hotel_details_on_map_button.setImageBitmap(it) })

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
        viewModel.mapImage.observe(this, mapImageObserver)
    }

    private companion object {
        const val DEFAULT_ACTIVITY_RESULT_HOTEL_ID: Long = -1
    }
}
