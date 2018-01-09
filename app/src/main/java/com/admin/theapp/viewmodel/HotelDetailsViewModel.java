package com.admin.theapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.admin.theapp.HotelsApp;
import com.admin.theapp.base.BaseViewModel;
import com.admin.theapp.interactors.DataInteractor;
import com.admin.theapp.model.HotelModel;
import com.admin.theapp.utils.mappers.HotelToHotelModelMapper;
import com.theapp.tools.tools.adapters.DisposableMaybeObserverAdapter;

import javax.inject.Inject;

public class HotelDetailsViewModel extends BaseViewModel {

    @Inject
    DataInteractor dataInteractor;

    @NonNull
    private final HotelToHotelModelMapper hotelToHotelModelMapper;

    @NonNull
    private final MutableLiveData<HotelModel> hotelModel = new MutableLiveData<>();

    @NonNull
    private final DisposableMaybeObserverAdapter<Hotel> hotelObserver = new DisposableMaybeObserverAdapter<Hotel>() {
        @Override
        public void onSuccess(@NonNull Hotel hotel) {
            hotelModel.setValue(hotelToHotelModelMapper.map(hotel));
        }
    };

    @Inject
    public HotelDetailsViewModel(@NonNull HotelsApp application,
                                 @NonNull HotelToHotelModelMapper hotelToHotelModelMapper) {
        super(application);
        this.hotelToHotelModelMapper = hotelToHotelModelMapper;
    }

    @NonNull
    public MutableLiveData<HotelModel> getHotelModel() {
        return hotelModel;
    }

    public void retrieveHotelModel(long hotelId) {
        execute(dataInteractor.getHotelById(hotelId), hotelObserver);
    }
}
