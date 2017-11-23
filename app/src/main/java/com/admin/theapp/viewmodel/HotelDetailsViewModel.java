package com.admin.theapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.admin.theapp.base.BaseViewModel;
import com.admin.theapp.interactors.DataInteractor;
import com.admin.theapp.model.HotelModel;
import com.admin.theapp.utils.mappers.HotelToHotelModelMapper;
import com.theapp.tools.tools.adapters.DisposableMaybeObserverAdapter;

public class HotelDetailsViewModel extends BaseViewModel {

    @NonNull
    private final DataInteractor              dataInteractor          = new DataInteractor();
    @NonNull
    private final MutableLiveData<HotelModel> hotelModel              = new MutableLiveData<>();
    @NonNull
    private final HotelToHotelModelMapper     hotelToHotelModelMapper = new HotelToHotelModelMapper();

    @NonNull
    private final DisposableMaybeObserverAdapter<Hotel> hotelObserver = new DisposableMaybeObserverAdapter<Hotel>() {
        @Override
        public void onSuccess(@NonNull Hotel hotel) {
            hotelModel.setValue(hotelToHotelModelMapper.map(hotel));
        }
    };

    public HotelDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    @NonNull
    public MutableLiveData<HotelModel> getHotelModel() {
        return hotelModel;
    }

    public void retrieveHotelModel(long hotelId) {
        execute(dataInteractor.getHotelById(hotelId), hotelObserver);
    }
}
