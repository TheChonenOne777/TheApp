package com.admin.theapp.dagger;

import com.admin.theapp.viewmodel.HotelDetailsViewModel;
import com.admin.theapp.viewmodel.HotelsViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    HotelsViewModel hotelsViewModel();

    HotelDetailsViewModel hotelDetailsViewModel();
}
