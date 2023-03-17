package com.girogevoro.gitapp.ui.screens

import com.girogevoro.gitapp.ui.main.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}