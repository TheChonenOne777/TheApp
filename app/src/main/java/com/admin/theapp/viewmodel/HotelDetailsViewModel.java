package com.admin.theapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.admin.theapp.HotelsApp;
import com.admin.theapp.base.BaseViewModel;
import com.admin.theapp.interactors.DataInteractor;
import com.admin.theapp.utils.Decoder;
import com.admin.theapp.utils.mappers.HotelToHotelModelMapper;
import com.theapp.entities.HotelModel;
import com.theapp.tools.Logger;
import com.theapp.tools.adapters.DisposableMaybeObserverAdapter;

import javax.inject.Inject;

public class HotelDetailsViewModel extends BaseViewModel {

    @NonNull
    private final DataInteractor          dataInteractor;
    @NonNull
    private final Decoder                 decoder;
    @NonNull
    private final HotelToHotelModelMapper hotelToHotelModelMapper;

    @NonNull
    private final MutableLiveData<HotelModel>     hotelModel = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<BitmapDrawable> hotelImage = new MutableLiveData<>();

    @NonNull
    private final DisposableMaybeObserverAdapter<Hotel> hotelObserver = new DisposableMaybeObserverAdapter<Hotel>() {
        @Override
        public void onSuccess(@NonNull Hotel hotel) {
            hotelModel.setValue(hotelToHotelModelMapper.map(hotel));
        }
    };

    @NonNull
    private final DisposableMaybeObserverAdapter<byte[]> hotelImageObserver = new DisposableMaybeObserverAdapter<byte[]>() {
        @Override
        public void onSuccess(@NonNull byte[] bytes) {
            hotelImage.setValue(decoder.decode(bytes));
        }
    };

    @Inject
    HotelDetailsViewModel(@NonNull HotelsApp application,
                          @NonNull Logger logger,
                          @NonNull HotelToHotelModelMapper hotelToHotelModelMapper,
                          @NonNull DataInteractor dataInteractor,
                          @NonNull Decoder decoder) {
        super(application, logger);
        this.hotelToHotelModelMapper = hotelToHotelModelMapper;
        this.dataInteractor = dataInteractor;
        this.decoder = decoder;
    }

    @NonNull
    public MutableLiveData<HotelModel> getHotelModel() {
        return hotelModel;
    }

    @NonNull
    public MutableLiveData<BitmapDrawable> getHotelImage() {
        return hotelImage;
    }

    public void retrieveHotelModel(long hotelId) {
        execute(dataInteractor.getHotelById(hotelId), hotelObserver);
    }

    public void getImageByName(@NonNull String imageName) {
        execute(dataInteractor.getBytes(imageName), hotelImageObserver);
    }
}
