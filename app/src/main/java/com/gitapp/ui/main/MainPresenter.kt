package com.gitapp.ui.main

import com.gitapp.ui.AppScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter() :
    MvpPresenter<MainView>() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var appScreen: AppScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(appScreen.discovery())
    }

    fun backClicked() {
        router.exit()
    }
}