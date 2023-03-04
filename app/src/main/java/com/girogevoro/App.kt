package com.girogevoro

import android.app.Application
import android.content.Context
import com.girogevoro.gitapp.data.users_repo.FakeUserRepoImpl
import com.girogevoro.gitapp.data.users_repo.UsersRepoImpl
import com.girogevoro.gitapp.domian.repos.UsersRepo

class App : Application() {
    val usersRepo: UsersRepo by lazy { UsersRepoImpl() }
}

val Context.app: App get() = applicationContext as App