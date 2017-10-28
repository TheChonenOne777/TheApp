package com.admin.theapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.admin.theapp.model.HotelModel;
import com.admin.theapp.parser.JSONParser;

import java.util.List;

public class HotelsViewModel extends AndroidViewModel implements LifecycleObserver {

    @NonNull
    private final MutableLiveData<List<HotelModel>> hotels = new MutableLiveData<>();
    @NonNull
    private final JSONParser parser;

    public HotelsViewModel(@NonNull Application application) {
        super(application);
        parser = new JSONParser(application);
        final List<HotelModel> hotelModels = parser.parseJsonsFromArray("0777.json");
        hotelModels.add(parser.parseJson("13100.json"));
        hotelModels.add(parser.parseJson("22470.json"));
        hotelModels.add(parser.parseJson("40611.json"));
        hotelModels.add(parser.parseJson("80899.json"));
        hotelModels.add(parser.parseJson("85862.json"));
        hotelModels.add(parser.parseJson("313499.json"));
        hotels.setValue(hotelModels);
    }

    @NonNull
    public MutableLiveData<List<HotelModel>> getHotels() {
        return hotels;
    }
}
