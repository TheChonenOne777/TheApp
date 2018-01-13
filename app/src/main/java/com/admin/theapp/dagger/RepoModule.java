package com.admin.theapp.dagger;

import com.admin.theapp.Hotel;
import com.admin.theapp.firebase.FirebaseDatabase;
import com.admin.theapp.utils.Constants;
import com.theapp.Cache;
import com.theapp.cache.HotelsCache;
import com.theapp.repository.Firebase;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
abstract class RepoModule {
    @Binds
    @Singleton
    abstract Firebase provideFirebase(FirebaseDatabase firebaseDatabase);

    @Provides
    @Singleton
    static Cache<Long, Hotel> provideHotelsCache() {
        return new HotelsCache(Constants.HOTELS_CACHE_SIZE);
    }
}
