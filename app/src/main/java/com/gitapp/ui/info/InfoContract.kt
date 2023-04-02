package com.gitapp.ui.info

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface InfoContract :MvpView{
    fun init()
    fun show(title:String, description:String)
}