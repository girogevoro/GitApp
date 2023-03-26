package com.girogevoro.gitapp.di

import com.girogevoro.gitapp.ui.screens.AndroidScreens
import com.girogevoro.gitapp.ui.screens.IScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    @Singleton
    @Provides
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun provideRouter(): Router = cicerone.router

    @Singleton
    @Provides
    fun provideScreens(): IScreens = AndroidScreens()
}