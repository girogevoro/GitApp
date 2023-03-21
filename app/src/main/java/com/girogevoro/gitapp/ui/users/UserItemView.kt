package com.girogevoro.gitapp.ui.users

import com.girogevoro.gitapp.domain.GithubUser

interface UserItemView: IItemView {
    fun setGitUser(gitHunUser: GithubUser)
}
