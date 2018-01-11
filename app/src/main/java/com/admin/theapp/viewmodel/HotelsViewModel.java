package com.admin.theapp.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.admin.theapp.HotelsApp;
import com.admin.theapp.base.BaseViewModel;
import com.admin.theapp.firebase.Firebase;
import com.admin.theapp.model.HotelModel;
import com.admin.theapp.utils.mappers.HotelToHotelModelMapper;
import com.theapp.tools.Logger;
import com.theapp.tools.adapters.DisposableObserverAdapter;

import java.util.List;

import javax.inject.Inject;

public class HotelsViewModel extends BaseViewModel {

    @NonNull
    private final HotelToHotelModelMapper mapper;
    @NonNull
    private final Firebase                firebase;

    @NonNull
    private final MutableLiveData<List<HotelModel>> hotels = new MutableLiveData<>();

    @Inject
    HotelsViewModel(@NonNull HotelsApp application,
                    @NonNull Logger logger,
                    @NonNull HotelToHotelModelMapper mapper,
                    @NonNull Firebase firebase) {
        super(application, logger);
        this.mapper = mapper;
        this.firebase = firebase;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onStart() {
        execute(firebase.getHotels(), new DisposableObserverAdapter<List<Hotel>>() {
            @Override
            public void onNext(@NonNull List<Hotel> hotelList) {
                hotels.setValue(mapper.map(hotelList));
            }
        });
    }

    @NonNull
    public MutableLiveData<List<HotelModel>> getHotels() {
        return hotels;
    }
}
