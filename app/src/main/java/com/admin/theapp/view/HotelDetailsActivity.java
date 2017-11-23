package com.admin.theapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.admin.theapp.R;
import com.admin.theapp.base.BaseActivity;
import com.admin.theapp.model.HotelModel;
import com.admin.theapp.viewmodel.HotelDetailsViewModel;

import butterknife.BindView;

public class HotelDetailsActivity extends BaseActivity<HotelDetailsViewModel> {

    private static final int DEFAULT_ACTIVITY_RESULT_HOTEL_ID = -1;

    @BindView(R.id.hotel_details_image)
    ImageView image;
    @BindView(R.id.hotel_details_name)
    TextView  name;
    @BindView(R.id.hotel_details_address)
    TextView  address;
    @BindView(R.id.hotel_details_stars)
    TextView  stars;
    @BindView(R.id.hotel_details_distance)
    TextView  distance;
    @BindView(R.id.hotel_details_suites_availability)
    TextView  suites_availability;
    @BindView(R.id.hotel_details_lat)
    TextView  lat;
    @BindView(R.id.hotel_details_lon)
    TextView  lon;

    private HotelDetailsViewModel viewModel;

    @NonNull
    private final Observer<HotelModel> hotelModelObserver = hotelModel -> {
        if (hotelModel != null) {
            name.setText(hotelModel.getName());
            address.setText(hotelModel.getAddress());
            stars.setText(String.valueOf(hotelModel.getStars()));
            distance.setText(String.valueOf(hotelModel.getDistance()));
            suites_availability.setText(hotelModel.getSuitesAvailability());
            lat.setText(String.valueOf(hotelModel.getLat()));
            lon.setText(String.valueOf(hotelModel.getLon()));
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final long hotelId = getIntent().getLongExtra(HotelsActivity.HOTEL_DETAILS_ACTIVITY_EXTRA, DEFAULT_ACTIVITY_RESULT_HOTEL_ID);
        if (hotelId == DEFAULT_ACTIVITY_RESULT_HOTEL_ID) {
            Toast.makeText(this, "Hotel does not exist", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        viewModel.retrieveHotelModel(hotelId);
        viewModel.getHotelModel().observe(this, hotelModelObserver);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.hotel_details;
    }

    @Override
    protected HotelDetailsViewModel getViewModel() {
        return viewModel = ViewModelProviders.of(this).get(HotelDetailsViewModel.class);
    }
}
