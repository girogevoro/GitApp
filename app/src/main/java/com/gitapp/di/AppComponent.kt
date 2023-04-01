package com.gitapp.di

import com.gitapp.ui.main.MainActivity
import com.gitapp.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CiceroneModule::class, MainUiModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun getMainPresenter(): MainPresenter
}