package com.admin.theapp.dagger

import com.admin.theapp.view.HotelDetailsActivity
import com.admin.theapp.view.HotelsActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidBindingModule {
    @ContributesAndroidInjector
    abstract fun contributeHotelsActivityInjector(): HotelsActivity

    @ContributesAndroidInjector
    abstract fun contributeHotelDetailsActivityInjector(): HotelDetailsActivity
}
