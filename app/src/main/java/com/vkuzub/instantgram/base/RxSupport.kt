package com.vkuzub.instantgram.base

import io.reactivex.disposables.Disposable

interface RxSupport {

    fun rxUnsubscribe()

    fun rxAddSubscription(disposable: Disposable?)

    fun initRxSubscriptions()

}