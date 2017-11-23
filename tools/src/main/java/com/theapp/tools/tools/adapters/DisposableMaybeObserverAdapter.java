package com.theapp.tools.tools.adapters;

import android.support.annotation.NonNull;

import io.reactivex.observers.DisposableMaybeObserver;

public abstract class DisposableMaybeObserverAdapter<T> extends DisposableMaybeObserver<T> {
    @Override
    public void onSuccess(@NonNull T t) { // empty stub for MaybeObservable method
    }

    @Override
    public void onError(@NonNull Throwable e) { // empty stub for MaybeObservable method
    }

    @Override
    public void onComplete() { // empty stub for MaybeObservable method
    }
}
