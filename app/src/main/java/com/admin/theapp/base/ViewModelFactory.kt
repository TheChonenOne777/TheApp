package com.admin.theapp.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.ArrayMap
import com.admin.theapp.dagger.ViewModelSubComponent
import com.admin.theapp.viewmodel.HotelDetailsViewModel
import com.admin.theapp.viewmodel.HotelsViewModel
import java.util.concurrent.Callable
import javax.inject.Inject

class ViewModelFactory @Inject constructor(viewModelSubComponent: ViewModelSubComponent) : ViewModelProvider.Factory {

    private val creators = ArrayMap<Class<out ViewModel>, Callable<out ViewModel>>()

    init {
        creators.put(HotelsViewModel::class.java, Callable { viewModelSubComponent.hotelsViewModel() })
        creators.put(HotelDetailsViewModel::class.java, Callable { viewModelSubComponent.hotelDetailsViewModel() })
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator = creators[modelClass]
        if (creator == null) {
            creators.entries.forEach {
                if (modelClass.isAssignableFrom(it.key)) {
                    creator = it.value
                    return@forEach
                }
            }
        }
        try {
            return creator!!.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
