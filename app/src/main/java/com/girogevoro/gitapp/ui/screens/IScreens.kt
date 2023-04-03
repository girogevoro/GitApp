package com.girogevoro.gitapp.ui.screens

import com.girogevoro.gitapp.domain.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userDetail(login: String): Screen
}
