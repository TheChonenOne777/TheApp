package com.admin.theapp.view;

import android.arch.lifecycle.Observer;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.admin.theapp.R;
import com.admin.theapp.base.BaseActivity;
import com.admin.theapp.model.HotelModel;
import com.admin.theapp.viewmodel.HotelDetailsViewModel;
import com.google.firebase.storage.StorageReference;

import javax.inject.Inject;

import butterknife.BindView;

public class HotelDetailsActivity extends BaseActivity<HotelDetailsViewModel> {

    private static final int    DEFAULT_ACTIVITY_RESULT_HOTEL_ID = -1;
    private static final String PATH_TO_IMAGE                    = "hotels/";
    private static final long   ONE_MEGABYTE                     = 1024 * 1024;

    @Inject
    StorageReference storageReference; // TODO: 1/11/2018 remove Storage reference from here

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
            if (hotelModel.getImageName() != null) {
                setImage(hotelModel.getImageName());
            }
        }
    };

    private void setImage(@NonNull String imageName) {
        storageReference.child(PATH_TO_IMAGE + imageName)
                        .getBytes(ONE_MEGABYTE)
                        .addOnSuccessListener(bytes -> image.setImageDrawable(new BitmapDrawable(
                                this.getResources(), BitmapFactory.decodeByteArray(bytes, 0, bytes.length))
                        )).addOnFailureListener(e -> Log.e("Failed to load image: ", e.getMessage()));
    }

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

    @NonNull
    @Override
    protected Class<HotelDetailsViewModel> getViewModelClass() {
        return HotelDetailsViewModel.class;
    }
}
