package com.admin.theapp.dagger;

import com.admin.theapp.Hotel;
import com.admin.theapp.firebase.FirebaseDatabaseImpl;
import com.admin.theapp.firebase.FirebaseStorageImpl;
import com.admin.theapp.utils.Constants;
import com.theapp.Cache;
import com.theapp.cache.HotelsCache;
import com.theapp.repository.FirebaseDatabase;
import com.theapp.repository.FirebaseStorage;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
abstract class RepoModule {
    @Binds
    @Singleton
    abstract FirebaseDatabase provideFirebase(FirebaseDatabaseImpl firebaseDatabase);

    @Binds
    @Singleton
    abstract FirebaseStorage provideFirebaseStorage(FirebaseStorageImpl firebaseStorage);

    @Provides
    @Singleton
    static Cache<Long, Hotel> provideHotelsCache() {
        return new HotelsCache(Constants.HOTELS_CACHE_SIZE);
    }
}
