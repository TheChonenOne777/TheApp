package com.admin.theapp;

import android.support.v7.app.AppCompatDelegate;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class HotelsApp extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
