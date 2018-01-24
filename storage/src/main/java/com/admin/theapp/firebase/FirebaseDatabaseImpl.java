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
import com.theapp.repository.FirebaseDatabase;
import com.theapp.tools.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;

public class FirebaseDatabaseImpl implements FirebaseDatabase {

    private static final String LOG_TAG = FirebaseDatabaseImpl.class.getSimpleName();

    @NonNull
    private final Logger                          logger;
    @NonNull
    private final FirebaseHotelModelToHotelMapper mapper;

    @NonNull
    private final BehaviorSubject<List<FirebaseHotelModel>> hotelsPublisher   = BehaviorSubject.create();
    @NonNull
    private final DatabaseReference                         hotelsDbReference = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("Hotels");

    @NonNull
    private final ValueEventListener hotelsValueListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            GenericTypeIndicator<List<FirebaseHotelModel>> gen = new GenericTypeIndicator<List<FirebaseHotelModel>>() {};
            final List<FirebaseHotelModel> list = dataSnapshot.getValue(gen);
            if (list != null) {
                hotelsPublisher.onNext(list);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            logger.e(LOG_TAG, "Failed to load: " + databaseError.getMessage());
        }
    };

    @Inject
    FirebaseDatabaseImpl(@NonNull Logger logger,
                         @NonNull FirebaseHotelModelToHotelMapper mapper) {
        this.logger = logger;
        this.mapper = mapper;
        hotelsDbReference.addListenerForSingleValueEvent(hotelsValueListener);
    }

    @NonNull
    @Override
    public Maybe<List<Hotel>> getHotels() {
        return hotelsPublisher.flatMapMaybe(hotels -> Maybe.fromCallable(() -> hotels))
                              .map(new Function<List<FirebaseHotelModel>, List<Hotel>>() {
                                  @Override
                                  public List<Hotel> apply(List<FirebaseHotelModel> from) throws Exception {
                                      return mapper.map(from);
                                  }
                              })
                              .firstElement();
    }

    @NonNull
    @Override
    public Maybe<Hotel> getHotelById(long id) {
        return hotelsPublisher.flatMapIterable(hotels -> hotels)
                              .filter(hotel -> hotel.getId() == id)
                              .map(mapper::map)
                              .firstElement();
    }
}
