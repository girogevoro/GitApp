package com.girogevoro.gitapp.ui.main

import com.girogevoro.gitapp.domain.GithubUser

interface UserItemView: IItemView {
    fun setGitUser(gitHunUser: GithubUser)
}
