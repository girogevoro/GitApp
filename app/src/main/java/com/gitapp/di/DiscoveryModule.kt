package com.gitapp.di

import com.gitapp.ui.AppScreens
import com.gitapp.ui.discovery.DiscoveryPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DiscoveryModule {

    @Singleton
    @Provides
    fun provideDiscoveryPresenter(router: Router, appScreens: AppScreens): DiscoveryPresenter =
        DiscoveryPresenter(router, appScreens)
}