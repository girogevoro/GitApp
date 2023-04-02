package com.gitapp.ui.discovery

import com.gitapp.ui.AppScreens
import com.gitapp.ui.BackButtonListener
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import java.time.LocalDate
import java.util.*

class DiscoveryPresenter(private val router: Router, private val appScreens: AppScreens) :
    MvpPresenter<DiscoveryContract>() {

    private val listenerKnow = fun(date: LocalDate) {
        viewState.showInfoDialog(date)
    }

    private val listenerHistory = fun() {
        router.navigateTo(appScreens.history())
    }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setOnListenerKnow(listenerKnow)
        viewState.setOnListenerHistory(listenerHistory)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}