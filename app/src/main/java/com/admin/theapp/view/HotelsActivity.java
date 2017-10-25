package com.admin.theapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.admin.theapp.R;
import com.admin.theapp.viewmodel.HotelsViewModel;

import butterknife.ButterKnife;

public class HotelsActivity extends AppCompatActivity {

    private HotelsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotels_layout);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(HotelsViewModel.class);
    }
}
