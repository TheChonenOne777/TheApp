package com.theapp.tools.tools.adapters;

import android.support.annotation.NonNull;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

public abstract class DisposableCompletableObserverAdapter implements CompletableObserver {
    @Override
    public void onSubscribe(@NonNull Disposable d) { // empty stub for Completable method
    }

    @Override
    public void onComplete() { // empty stub for Completable method
    }

    @Override
    public void onError(@NonNull Throwable e) { // empty stub for Completable method
    }
}
