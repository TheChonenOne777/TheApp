package com.admin.theapp.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.admin.theapp.R;

import java.io.IOException;

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
        initFields();
    }

    private void initFields() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name.setText(extras.getString("name"));
            address.setText(extras.getString("address"));
            stars.setText(getResources().getString(R.string.hotel_details_stars, extras.getDouble("stars")));
            distance.setText(getResources().getString(R.string.hotel_details_distance, extras.getDouble("distance")));
            suites_availability.setText(getResources().getString(R.string.hotel_details_suites_availability, extras.getString("suites_availability")));
            lat.setText(getResources().getString(R.string.hotel_details_lat, extras.getDouble("lat")));
            lon.setText(getResources().getString(R.string.hotel_details_lon, extras.getDouble("lon")));
            image.setImageDrawable(getImageFromAssets(extras.getString("image")));
            image.setAdjustViewBounds(true);
        }
    }

    @Nullable
    private Drawable getImageFromAssets(String imageName) {
        try {
            return Drawable.createFromStream(getAssets().open(imageName), null);
        } catch (IOException ignored) {
        }
        return null;
    }
}
