package com.admin.theapp.dagger;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.admin.theapp.HotelsApp;
import com.admin.theapp.base.ViewModelFactory;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = ViewModelSubComponent.class)
abstract class AppModule {

    @Provides
    @Singleton
    static Context provideContext(HotelsApp application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    static ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder viewModelSubComponent) {
        return new ViewModelFactory(viewModelSubComponent.build());
    }

    @Provides
    @Singleton
    static StorageReference provideStorageReference() {
        return FirebaseStorage.getInstance().getReference();
    }
}
