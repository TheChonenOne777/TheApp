package com.admin.theapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.admin.theapp.HotelsApp;
import com.admin.theapp.base.BaseViewModel;
import com.admin.theapp.model.FirebaseHotelModel;
import com.admin.theapp.model.HotelModel;
import com.admin.theapp.utils.mappers.FirebaseHotelModelToHotelModelMapper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.theapp.tools.Logger;

import java.util.List;

import javax.inject.Inject;

public class HotelsViewModel extends BaseViewModel {

    private final DatabaseReference hotelsDbReference = FirebaseDatabase.getInstance().getReference("Hotels");

    @NonNull
    private final FirebaseHotelModelToHotelModelMapper mapper;

    @NonNull
    private final MutableLiveData<List<HotelModel>> hotels = new MutableLiveData<>();

    @NonNull
    private final ValueEventListener hotelsValueListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            GenericTypeIndicator<List<FirebaseHotelModel>> gen = new GenericTypeIndicator<List<FirebaseHotelModel>>() {};
            final List<FirebaseHotelModel> list = dataSnapshot.getValue(gen);
            if (list != null) {
                hotels.setValue(mapper.map(list));
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            logError("Failed to load: " + databaseError.getMessage());
        }
    };

    @Inject
    public HotelsViewModel(@NonNull HotelsApp application,
                           @NonNull Logger logger,
                           @NonNull FirebaseHotelModelToHotelModelMapper mapper) {
        super(application, logger);
        this.mapper = mapper;
        hotelsDbReference.addListenerForSingleValueEvent(hotelsValueListener);
    }

    @NonNull
    public MutableLiveData<List<HotelModel>> getHotels() {
        return hotels;
    }
}
