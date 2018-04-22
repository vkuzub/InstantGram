package com.vkuzub.instantgram.base.view

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vkuzub.instantgram.R

interface HideShowContentView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showContent()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(msg: String?)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(@StringRes msg: Int = R.string.oops_something_went_wrong)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showEmpty(msg: String?)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showEmpty(@StringRes msg: Int = R.string.no_data)

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(msg: String?)

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(msg: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgressDialog()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideProgressDialog()
}