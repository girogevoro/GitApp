package com.girogevoro.gitapp.ui.navigation

import android.content.Context
import com.girogevoro.gitapp.domian.entities.UserEntity
import com.github.terrakok.cicerone.Screen

interface ScreensApp {
    fun users(): Screen
    fun userInfo(userEntity: UserEntity): Screen
}