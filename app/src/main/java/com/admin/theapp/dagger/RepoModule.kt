package com.admin.theapp.dagger

import android.util.LruCache
import com.admin.theapp.Hotel
import com.admin.theapp.firebase.FirebaseDatabaseImpl
import com.admin.theapp.firebase.FirebaseStorageImpl
import com.admin.theapp.utils.Constants
import com.theapp.Cache
import com.theapp.cache.HotelsCache
import com.theapp.repository.Database
import com.theapp.repository.Storage
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepoModule {
    @Binds
    @Singleton
    abstract fun provideFirebaseDatabase(firebaseDatabase: FirebaseDatabaseImpl): Database

    @Binds
    @Singleton
    abstract fun provideFirebaseStorage(firebaseStorage: FirebaseStorageImpl): Storage

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun provideHotelsCache(): Cache<Long, Hotel> = HotelsCache(LruCache(Constants.HOTELS_CACHE_SIZE))
    }
}
