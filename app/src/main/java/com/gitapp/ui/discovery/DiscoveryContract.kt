package com.gitapp.ui.discovery

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.time.LocalDate
import java.util.*

@StateStrategyType(AddToEndSingleStrategy::class)
interface DiscoveryContract : MvpView {
    fun setOnListenerKnow(listener: (LocalDate) -> Unit)
    fun setOnListenerHistory(listener: () -> Unit)

    fun showInfoDialog(date: LocalDate)
}