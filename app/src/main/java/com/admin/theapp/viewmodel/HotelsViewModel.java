package com.admin.theapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

import com.admin.theapp.model.HotelModel;
import com.admin.theapp.parser.JSONParser;

public class HotelsViewModel extends AndroidViewModel implements LifecycleObserver {

    @NonNull
    private final MutableLiveData<HotelModel> hotel = new MutableLiveData<>();
    @NonNull
    private final JSONParser parser;

    public HotelsViewModel(@NonNull Application application) {
        super(application);
        parser = new JSONParser(application);
    }

    @NonNull
    public MutableLiveData<HotelModel> getHotel() {
        return hotel;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(){

    }

    public void onGetJsonClick() {
        hotel.setValue(parser.parseJson("22470.json"));
    }
}
