package com.admin.theapp.base

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.admin.theapp.R
import com.admin.theapp.utils.visible
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.base_activity.*
import javax.inject.Inject

abstract class BaseActivityNew<T : BaseViewModelNew> : AppCompatActivity() {

    protected abstract val vmClass: Class<T>

    protected abstract val layoutRes: Int

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    protected val viewModel: T by lazy {
        ViewModelProviders.of(this, vmFactory).get(vmClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
        main_viewstub.layoutResource = layoutRes
        main_viewstub.visible(true)
        setSupportActionBar(app_toolbar)
        lifecycle.addObserver(viewModel)
    }

    override fun onDestroy() {
        lifecycle.removeObserver(viewModel)
        super.onDestroy()
    }
}