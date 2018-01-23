package com.admin.theapp.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.admin.theapp.HotelsApp;
import com.admin.theapp.base.BaseViewModel;
import com.admin.theapp.interactors.DataInteractor;
import com.admin.theapp.model.HotelModel;
import com.admin.theapp.utils.mappers.HotelToHotelModelMapper;
import com.theapp.tools.Logger;
import com.theapp.tools.adapters.DisposableMaybeObserverAdapter;

import java.util.List;

import javax.inject.Inject;

public class HotelsViewModel extends BaseViewModel {

    @NonNull
    private final HotelToHotelModelMapper mapper;
    @NonNull
    private final DataInteractor          interactor;

    @NonNull
    private final MutableLiveData<List<HotelModel>> hotels = new MutableLiveData<>();

    @NonNull
    private final DisposableMaybeObserverAdapter<List<Hotel>> hotelsObservable = new DisposableMaybeObserverAdapter<List<Hotel>>() {
        @Override
        public void onSuccess(@NonNull List<Hotel> hotelList) {
            hotels.setValue(mapper.map(hotelList));
        }
    };

    @Inject
    HotelsViewModel(@NonNull HotelsApp application,
                    @NonNull Logger logger,
                    @NonNull HotelToHotelModelMapper mapper,
                    @NonNull DataInteractor interactor) {
        super(application, logger);
        this.mapper = mapper;
        this.interactor = interactor;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
//        execute(interactor.getHotels(), new DisposableObserverAdapter<List<Hotel>>() {
//            @Override
//            public void onNext(@NonNull List<Hotel> hotelList) {
//                hotels.setValue(mapper.map(hotelList));
//            }
//        });
        execute(interactor.getHotels(), hotelsObservable);
    }

    @NonNull
    public MutableLiveData<List<HotelModel>> getHotels() {
        return hotels;
    }
}
