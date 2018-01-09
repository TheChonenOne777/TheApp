package com.admin.theapp.dagger;

import android.content.Context;

import com.admin.theapp.HotelsApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
abstract class AppModule {

    @Provides
    @Singleton
    static Context provideContext(HotelsApp application) {
        return application.getApplicationContext();
    }
}
