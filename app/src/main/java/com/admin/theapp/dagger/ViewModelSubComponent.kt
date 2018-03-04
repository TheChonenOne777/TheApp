package com.admin.theapp.dagger

import com.admin.theapp.viewmodel.HotelDetailsViewModel
import com.admin.theapp.viewmodel.HotelsViewModel

import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun hotelsViewModel(): HotelsViewModel

    fun hotelDetailsViewModel(): HotelDetailsViewModel
}
