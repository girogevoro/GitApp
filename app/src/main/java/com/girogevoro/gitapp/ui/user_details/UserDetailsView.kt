package com.girogevoro.gitapp.ui.user_details

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserDetailsView : MvpView {
    fun init()
    fun showUserLogin(login: String)
}