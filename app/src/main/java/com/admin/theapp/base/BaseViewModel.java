package com.admin.theapp.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.support.annotation.NonNull;

import com.theapp.tools.Logger;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.internal.operators.maybe.MaybeCallbackObserver;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @deprecated Use {@link BaseViewModelNew}
 */
@Deprecated
public abstract class BaseViewModel extends AndroidViewModel implements LifecycleObserver {

    @NonNull
    private final Logger logger;

    @NonNull
    protected final CompositeDisposable disposables = new CompositeDisposable();


    public BaseViewModel(@NonNull Application application,
                         @NonNull Logger logger) {
        super(application);
        this.logger = logger;
    }

    @Override
    protected void onCleared() {
        disposables.dispose();
        super.onCleared();
    }

    public void addObserver(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    public void removeObserver(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @NonNull
    protected final Scheduler getBackgroundScheduler() {
        return Schedulers.io();
    }

    @NonNull
    protected final Scheduler getMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @NonNull
    protected final <E> Disposable execute(@NonNull Observable<E> observable, @NonNull DisposableObserver<E> observer) {

        observable.subscribeOn(getBackgroundScheduler())
                  .observeOn(getMainThreadScheduler())
                  .subscribeWith(observer);
        disposables.add(observer);
        return observer;
    }

    @NonNull
    protected final <E> Disposable execute(@NonNull Maybe<E> observable, @NonNull MaybeObserver<E> observer) {
        final MaybeCallbackObserver<E> maybeCallObserver = new MaybeCallbackObserver<>(observer::onSuccess, observer::onError, observer::onComplete);

        observable.subscribeOn(getBackgroundScheduler())
                  .observeOn(getMainThreadScheduler())
                  .subscribe(maybeCallObserver);

        disposables.add(maybeCallObserver);
        return maybeCallObserver;
    }

    @NonNull
    protected final <E> Disposable execute(@NonNull Single<E> observable, @NonNull SingleObserver<E> observer) {
        final ConsumerSingleObserver<E> consumerSingleObserver = new ConsumerSingleObserver<>(observer::onSuccess, observer::onError);

        observable.subscribeOn(getBackgroundScheduler())
                  .observeOn(getMainThreadScheduler())
                  .subscribe(consumerSingleObserver);

        disposables.add(consumerSingleObserver);
        return consumerSingleObserver;
    }

    protected final Disposable execute(@NonNull Completable observable, @NonNull CompletableObserver observer) {
        final DisposableCompletableObserver completableObserver = new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                observer.onComplete();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                observer.onError(e);
            }
        };

        observable.subscribeOn(getBackgroundScheduler())
                  .observeOn(getMainThreadScheduler())
                  .subscribe(completableObserver);

        disposables.add(completableObserver);
        return completableObserver;
    }

    @NonNull
    protected final <E> Function<Throwable, Observable<E>> logErrorAndResumeEmpty() {
        return logError(Observable.<E>empty());
    }

    @NonNull
    protected final <E> Function<Throwable, Observable<E>> logAndResumeErrorObservable() {
        return throwable -> {
            logError(throwable);
            return Observable.error(throwable);
        };
    }

    @NonNull
    protected final <E> Function<Throwable, Single<E>> logAndResumeErrorSingle() {
        return throwable -> {
            logError(throwable);
            return Single.error(throwable);
        };
    }

    @NonNull
    protected final <E> Function<Throwable, Observable<E>> logError(Observable<E> observable) {
        return throwable -> {
            logError(throwable);
            return observable;
        };
    }

    protected void logError(@NonNull Throwable throwable) {
        logger.e(getClass().getSimpleName(), "Error " + throwable.getMessage(), throwable);
    }

    protected void logError(@NonNull String message) {
        logger.e(getClass().getSimpleName(), "Error " + message);
    }
}
