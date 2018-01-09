package com.admin.theapp.dagger;

import com.admin.theapp.HotelsApp;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidBindingModule.class,
        AppModule.class
})
interface AppComponent extends AndroidInjector<HotelsApp> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<HotelsApp> {
    }
}
