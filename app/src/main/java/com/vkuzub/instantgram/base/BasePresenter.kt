package com.vkuzub.instantgram.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

open class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun rxAddSubscription(disposable: Disposable?) {
        if (disposable != null)
            compositeDisposable.add(disposable)
    }

    fun logException(throwable: Throwable?) {
        if (throwable != null) {
            Timber.i(throwable)
        }
    }
}