package com.gitapp.di

import com.gitapp.ui.AppScreens
import com.gitapp.ui.main.MainPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainUiModule {
    @Singleton
    @Provides
    fun provideMainPresenter(router: Router, appScreen: AppScreens): MainPresenter =
        MainPresenter(router, appScreen)

}