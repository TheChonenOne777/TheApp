package com.admin.theapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

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

import java.util.List;

public class HotelsViewModel extends BaseViewModel {

    @NonNull
    private final MutableLiveData<List<HotelModel>>    hotels    = new MutableLiveData<>();
    @NonNull
    private final FirebaseDatabase                     database  = FirebaseDatabase.getInstance();
    @NonNull
    private final DatabaseReference                    reference = database.getReference("Hotels");
    @NonNull
    private final FirebaseHotelModelToHotelModelMapper mapper    = new FirebaseHotelModelToHotelModelMapper();

    @NonNull
    private final ValueEventListener valueEventListener = new ValueEventListener() {
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
            Log.e("Failed to load: ", databaseError.getMessage());
        }
    };

    public HotelsViewModel(@NonNull Application application) {
        super(application);
        reference.addListenerForSingleValueEvent(valueEventListener);
    }

    @NonNull
    public MutableLiveData<List<HotelModel>> getHotels() {
        return hotels;
    }
}
