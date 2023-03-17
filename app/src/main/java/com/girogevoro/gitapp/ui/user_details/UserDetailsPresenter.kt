package com.girogevoro.gitapp.ui.user_details

import com.girogevoro.gitapp.domain.GithubUser
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDetailsPresenter(private val router: Router) : MvpPresenter<UserDetailsView>() {

    var user: GithubUser? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        user?.login?.let { viewState.showUserLogin(it) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}