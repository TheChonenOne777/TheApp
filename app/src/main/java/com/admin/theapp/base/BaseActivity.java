package com.admin.theapp.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;

import com.admin.theapp.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.app_toolbar)
    Toolbar toolbar;

    protected T viewModel;

    private final LifecycleRegistry registry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        ViewStub viewStub = findViewById(R.id.main_viewstub);
        viewStub.setLayoutResource(getLayoutRes());
        viewStub.setVisibility(View.VISIBLE);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass());
        viewModel.addObserver(getLifecycle());
    }

    @Override
    protected void onDestroy() {
        viewModel.removeObserver(getLifecycle());
        super.onDestroy();
    }

    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return registry;
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    @NonNull
    protected abstract Class<T> getViewModelClass();
}
