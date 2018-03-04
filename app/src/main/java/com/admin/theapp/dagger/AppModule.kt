package com.admin.theapp.dagger

import android.arch.lifecycle.ViewModelProvider
import com.admin.theapp.BuildConfig
import com.admin.theapp.HotelsApp
import com.admin.theapp.base.ViewModelFactory
import com.admin.theapp.logger.AppLogger
import com.admin.theapp.logger.ReleaseLogger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [(ViewModelSubComponent::class)])
object AppModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideContext(application: HotelsApp) = application.applicationContext

    @Provides
    @JvmStatic
    @Singleton
    fun provideViewModelFactory(viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory =
        ViewModelFactory(viewModelSubComponent.build())

    @Provides
    @JvmStatic
    @Singleton
    fun provideLogger() = if (BuildConfig.DEBUG) AppLogger() else ReleaseLogger()
}
