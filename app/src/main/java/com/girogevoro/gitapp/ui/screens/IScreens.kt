package com.girogevoro.gitapp.ui.screens

import com.girogevoro.gitapp.domain.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userDetail(user: GithubUser): Screen
}
