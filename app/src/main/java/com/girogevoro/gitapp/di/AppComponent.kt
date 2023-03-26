package com.girogevoro.gitapp.di

import com.girogevoro.gitapp.ui.content_activity.MainActivity
import com.girogevoro.gitapp.ui.content_activity.MainPresenter
import com.girogevoro.gitapp.ui.user_details.UserDetailsPresenter
import com.girogevoro.gitapp.ui.users.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CiceroneModule::class, PresentersModule::class, ReposModule::class,
    AndroidModule::class])
interface AppComponent {
    fun getMainPresenter(): MainPresenter
    fun getUserDetailsPresenter(): UserDetailsPresenter
    fun getUserPresenter(): UsersPresenter

    fun inject(mainActivity: MainActivity)
}