package com.admin.theapp.base

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.theapp.tools.Logger
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Maybe
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModelNew(private val logger: Logger) : ViewModel(), LifecycleObserver {

    private val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    protected fun <T> Maybe<T>.execute(onSuccess: (T) -> Unit = emptyType(),
                                       onComplete: () -> Unit = emptyComplete(),
                                       onError: (Throwable) -> Unit = defaultError(),
                                       onSubscribe: (Disposable) -> Unit = emptySubscribe(),
                                       onFinished: () -> Unit = emptyFinished()) {

        observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : MaybeObserver<T> {
                    override fun onSuccess(t: T) {
                        onSuccess(t)
                        onFinished()
                    }

                    override fun onComplete() {
                        onComplete()
                        onFinished()
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d)
                        onSubscribe(d)
                    }

                    override fun onError(e: Throwable) {
                        onError(e)
                        onFinished()
                    }
                })
    }

    protected fun Completable.execute(onComplete: () -> Unit = emptyComplete(),
                                      onError: (Throwable) -> Unit = defaultError(),
                                      onSubscribe: (Disposable) -> Unit = emptySubscribe(),
                                      onFinished: () -> Unit = emptyFinished()) {

        observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : CompletableObserver {
                    override fun onComplete() {
                        onComplete()
                        onFinished()
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d)
                        onSubscribe(d)
                    }

                    override fun onError(e: Throwable) {
                        onError(e)
                        onFinished()
                    }
                })
    }

    private fun <T> emptyType(): (T) -> Unit = {}

    private fun emptyComplete(): () -> Unit = {}

    private fun defaultError(): (Throwable) -> Unit = {
        logger.e(BaseViewModelNew::class.java.simpleName, it.message ?: "", it)
    }

    private fun emptySubscribe(): (Disposable) -> Unit = {}

    private fun emptyFinished(): () -> Unit = {}
}