package com.gitapp.ui.main

import com.gitapp.ui.AppScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val appScreen: AppScreens) :
    MvpPresenter<MainContract>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(appScreen.discovery())
    }

    fun backClicked() {
        router.exit()
    }
}