package com.girogevoro

import android.app.Application
import android.content.Context
import com.girogevoro.gitapp.data.users_repo.UsersRepoImpl
import com.girogevoro.gitapp.domian.repos.UsersRepo
import com.github.terrakok.cicerone.Cicerone

class App : Application() {
    companion object{
        lateinit var instance: App
    }


    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    val usersRepo: UsersRepo by lazy { UsersRepoImpl() }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

val Context.app: App get() = applicationContext as App