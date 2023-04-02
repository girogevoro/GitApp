package com.gitapp.di

import com.gitapp.domain.HistoryRepo
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
    fun provideInfoPresenter(infoRepo: InfoRepo, historyRepo: HistoryRepo): InfoPresenter =
        InfoPresenter(infoRepo, historyRepo)

    @Singleton
    @Provides
    fun provideInfoDialogFragmentFabric(): InfoDialogFragmentFabric = InfoDialogFragmentFabric()
}