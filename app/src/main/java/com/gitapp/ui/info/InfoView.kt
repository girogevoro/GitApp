package com.gitapp.ui.info

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface InfoView :MvpView{
    fun init()
    fun showInformation(title:String, description:String)
    fun showLoading()
    fun showError()
}