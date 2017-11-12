package com.admin.theapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.admin.theapp.R;
import com.admin.theapp.model.HotelModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotelDetailsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        final long hotelId = Long.parseLong(getIntent().getStringExtra(HotelsActivity.HOTEL_DETAILS_ACTIVITY_EXTRA));
        HotelModel hotelModel = retrieveHotel(hotelId);
    }

    @Nullable
    private HotelModel retrieveHotel(long hotelId) {
        return null;
    }
}
