package com.admin.theapp.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.theapp.repository.Storage;
import com.theapp.tools.Logger;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.subjects.PublishSubject;

public class FirebaseStorageImpl implements Storage {

    private static final String LOG_TAG       = FirebaseStorageImpl.class.getSimpleName();
    private static final String PATH_TO_IMAGE = "hotels/";
    private static final long   ONE_MEGABYTE  = 1024 * 1024;

    @NonNull
    private final StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @NonNull
    private final Logger logger;

    @Inject
    FirebaseStorageImpl(@NonNull Logger logger) {
        this.logger = logger;
    }

    @NonNull
    @Override
    public Maybe<byte[]> getBytes(@NonNull String name) {
        final PublishSubject<byte[]> subject = PublishSubject.create();
        storageReference.child(PATH_TO_IMAGE + name)
                        .getBytes(ONE_MEGABYTE)
                        .addOnSuccessListener(subject::onNext)
                        .addOnFailureListener(e -> logger.e(LOG_TAG, "Failed to load data: " + e.getMessage()));
        return subject.flatMapMaybe(bytes -> Maybe.fromCallable(() -> bytes)).firstElement();
    }
}
