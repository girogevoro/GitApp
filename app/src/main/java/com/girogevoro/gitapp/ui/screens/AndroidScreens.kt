package com.girogevoro.gitapp.ui.screens

import com.girogevoro.gitapp.domain.GithubUser
import com.girogevoro.gitapp.ui.users.UsersFragment
import com.girogevoro.gitapp.ui.user_details.UserDetailsFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun userDetail(login: String) =
        FragmentScreen { UserDetailsFragment.newInstance(login) }
}
