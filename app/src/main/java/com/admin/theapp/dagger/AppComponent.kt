package com.admin.theapp.dagger

import com.admin.theapp.HotelsApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidBindingModule::class,
    AppModule::class,
    RepoModule::class
])
interface AppComponent : AndroidInjector<HotelsApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<HotelsApp>()
}
