package com.admin.theapp.dagger;

import android.util.LruCache;

import com.admin.theapp.Hotel;
import com.admin.theapp.firebase.FirebaseDatabaseImpl;
import com.admin.theapp.firebase.FirebaseStorageImpl;
import com.admin.theapp.utils.Constants;
import com.theapp.Cache;
import com.theapp.cache.HotelsCache;
import com.theapp.repository.Database;
import com.theapp.repository.Storage;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
abstract class RepoModule {
    @Binds
    @Singleton
    abstract Database provideFirebaseDatabase(FirebaseDatabaseImpl firebaseDatabase);

    @Binds
    @Singleton
    abstract Storage provideFirebaseStorage(FirebaseStorageImpl firebaseStorage);

    @Provides
    @Singleton
    static Cache<Long, Hotel> provideHotelsCache() {
        return new HotelsCache(new LruCache<>(Constants.HOTELS_CACHE_SIZE));
    }
}
