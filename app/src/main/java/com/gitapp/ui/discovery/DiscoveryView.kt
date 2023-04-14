package com.gitapp.ui.discovery

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.time.LocalDate

interface DiscoveryView : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun showInfoDialog(date: LocalDate)
}