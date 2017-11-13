package com.admin.theapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.admin.theapp.R;
import com.admin.theapp.model.HotelModel;
import com.admin.theapp.viewmodel.HotelsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotelsActivity extends AppCompatActivity implements HotelsAdapter.ItemClickListener {

    public static final String HOTEL_DETAILS_ACTIVITY_EXTRA = "id";
    @BindView(R.id.hotels_recycler_view)
    RecyclerView hotelsRecyclerView;

    HotelsAdapter hotelsAdapter;
    private HotelsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotels_layout);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(HotelsViewModel.class);
        hotelsAdapter = new HotelsAdapter(this);
        hotelsAdapter.setOnClickCallback(this);
        hotelsRecyclerView.setAdapter(hotelsAdapter);
        hotelsRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        hotelsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel.getHotels().observe(this, hotelModels -> hotelsAdapter.setData(hotelModels));
    }

    @Override
    public void onClick(@NonNull HotelModel hotelModel) {
        Intent intent = new Intent(this, HotelDetailsActivity.class);
        intent.putExtra(HOTEL_DETAILS_ACTIVITY_EXTRA, hotelModel.getId());
        startActivity(intent);
    }
}
