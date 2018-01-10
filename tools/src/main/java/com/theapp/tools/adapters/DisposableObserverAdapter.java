package com.theapp.tools.adapters;


import android.support.annotation.NonNull;

import io.reactivex.observers.DisposableObserver;

public abstract class DisposableObserverAdapter<T> extends DisposableObserver<T> {

    @Override
    public void onNext(@NonNull T t) { // can be overridden in successors
    }

    @Override
    public void onError(@NonNull Throwable e) { // can be overridden in successors
    }

    @Override
    public void onComplete() { // can be overridden in successors
    }
}
