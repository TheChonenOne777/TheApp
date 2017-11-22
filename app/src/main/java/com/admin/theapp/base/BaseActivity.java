package com.admin.theapp.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {

    private final LifecycleRegistry registry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        getViewModel().addObserver(getLifecycle());
    }

    @Override
    protected void onDestroy() {
        getViewModel().removeObserver(getLifecycle());
        super.onDestroy();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return registry;
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract T getViewModel();

}
