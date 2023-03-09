package com.girogevoro.gitapp.ui.navigation

import android.content.Context
import com.girogevoro.App
import com.girogevoro.gitapp.domian.entities.UserEntity
import com.girogevoro.gitapp.ui.userInfo.UserInfoActivity
import com.girogevoro.gitapp.ui.users.UsersListActivity
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.ActivityScreen

class ScreensAppImpl : ScreensApp {

    override fun users(): Screen {
        return ActivityScreen { UsersListActivity.instance(App.instance) }
    }

    override fun userInfo( userEntity: UserEntity): Screen {
        return ActivityScreen {
            UserInfoActivity.instance(App.instance, userEntity)
        }
    }

}