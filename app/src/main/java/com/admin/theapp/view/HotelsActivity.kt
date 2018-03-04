package com.admin.theapp.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.admin.theapp.R
import com.admin.theapp.base.BaseActivity
import com.admin.theapp.utils.Constants
import com.admin.theapp.viewmodel.HotelsViewModel
import com.theapp.entities.HotelModel
import kotlinx.android.synthetic.main.hotels_list.*
import javax.inject.Inject

class HotelsActivity : BaseActivity<HotelsViewModel>(), HotelsAdapter.ItemClickListener {

    override val vmClass = HotelsViewModel::class.java

    override val layoutRes = R.layout.hotels_list

    @Inject
    lateinit var hotelsAdapter: HotelsAdapter

    private val hotelsObserver = Observer<List<HotelModel>> { it?.let { hotelsAdapter.setData(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hotelsAdapter.setOnClickCallback(this)
        hotels_recycler_view.apply {
            adapter = hotelsAdapter
            layoutManager = LinearLayoutManager(this@HotelsActivity)
            addItemDecoration(DividerItemDecoration(this@HotelsActivity, DividerItemDecoration.VERTICAL))
        }
        viewModel.hotels.observe(this, hotelsObserver)
    }

    override fun onClick(hotelModel: HotelModel) {
        val intent = Intent(this, HotelDetailsActivity::class.java)
        intent.putExtra(Constants.EXTRA_HOTEL_DETAILS_ACTIVITY, hotelModel.id)
        startActivity(intent)
    }
}
