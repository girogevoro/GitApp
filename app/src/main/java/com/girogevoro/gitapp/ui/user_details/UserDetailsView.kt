package com.girogevoro.gitapp.ui.user_details

import com.girogevoro.gitapp.domain.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserDetailsView : MvpView {
    fun init()
    fun showUser(githubUser: GithubUser)
    fun updateRecyclerView()
    fun openUserRepo(repoUrl: String)
}