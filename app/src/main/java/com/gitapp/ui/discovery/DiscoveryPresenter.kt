package com.gitapp.ui.discovery

import com.gitapp.ui.AppScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import java.time.LocalDate
import javax.inject.Inject

class DiscoveryPresenter() :
    MvpPresenter<DiscoveryView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var appScreens: AppScreens

    fun clickKnow(date: LocalDate) {
        viewState.showInfoDialog(date)
    }

    fun clickHistory() {
        router.navigateTo(appScreens.history())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}