package com.admin.theapp.firebase;

import android.support.annotation.NonNull;

import com.admin.theapp.Hotel;
import com.admin.theapp.firebase.model.FirebaseHotelModel;
import com.admin.theapp.firebase.utils.mappers.FirebaseHotelModelToHotelMapper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.theapp.repository.Firebase;
import com.theapp.tools.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class FirebaseDatabase implements Firebase {

    @NonNull
    private static final String LOG_TAG = FirebaseDatabase.class.getSimpleName();

    @NonNull
    private final Logger                          logger;
    @NonNull
    private final FirebaseHotelModelToHotelMapper mapper;

    private List<FirebaseHotelModel> tempStorage; // TODO: 1/11/2018 to be replaced or removed

    @NonNull
    private final PublishSubject<List<FirebaseHotelModel>> hotelsPublisher   = PublishSubject.create();
    @NonNull
    private final DatabaseReference                        hotelsDbReference = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("Hotels");

    @NonNull
    private final ValueEventListener hotelsValueListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            GenericTypeIndicator<List<FirebaseHotelModel>> gen = new GenericTypeIndicator<List<FirebaseHotelModel>>() {};
            final List<FirebaseHotelModel> list = dataSnapshot.getValue(gen);
            if (list != null) {
                hotelsPublisher.onNext(list);
            }
            tempStorage = list;
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            logger.e(LOG_TAG, "Failed to load: " + databaseError.getMessage());
        }
    };

    @Inject
    FirebaseDatabase(@NonNull Logger logger,
                     @NonNull FirebaseHotelModelToHotelMapper mapper) {
        this.logger = logger;
        this.mapper = mapper;
        hotelsDbReference.addListenerForSingleValueEvent(hotelsValueListener);
    }

    @NonNull
    @Override
    public Observable<List<Hotel>> getHotels() {
        return hotelsPublisher.flatMap(hotels -> Observable.fromCallable(() -> hotels))
                              .map(mapper::map);
    }

    @NonNull
    @Override
    public Maybe<Hotel> getHotelById(long id) {
        if (tempStorage == null) {
            return Maybe.empty();
        }
        for (FirebaseHotelModel hotelModel : tempStorage) {
            if (hotelModel.getId() == id) {
                return Maybe.fromCallable(() -> hotelModel).map(mapper::map);
            }
        }
        return Maybe.empty();
    }
}
