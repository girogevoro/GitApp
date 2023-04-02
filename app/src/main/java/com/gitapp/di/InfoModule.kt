package com.gitapp.di

import com.gitapp.domain.InfoRepo
import com.gitapp.ui.info.InfoDialogFragmentFabric
import com.gitapp.ui.info.InfoPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InfoModule {
    @Singleton
    @Provides
    fun provideInfoPresenter(infoRepo: InfoRepo): InfoPresenter = InfoPresenter(infoRepo)

    @Singleton
    @Provides
    fun provideInfoDialogFragmentFabric(): InfoDialogFragmentFabric = InfoDialogFragmentFabric()
}