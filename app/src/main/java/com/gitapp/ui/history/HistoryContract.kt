package com.gitapp.ui.history

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface HistoryContract : MvpView {
    fun init()
    fun updateList()
}
