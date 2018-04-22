package com.vkuzub.instantgram.base.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface HideShowContentInteractionView : MvpView{

    fun onErrorViewClick()

    fun onEmptyViewClick()
}