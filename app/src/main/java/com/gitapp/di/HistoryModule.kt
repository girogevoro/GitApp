package com.gitapp.di

import com.gitapp.domain.HistoryRepo
import com.gitapp.ui.AppScreens
import com.gitapp.ui.history.HistoryPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class HistoryModule {
    @Provides
    fun provideHistoryPresenter(
        historyRepo: HistoryRepo,
        router: Router,
        appScreens: AppScreens
    ): HistoryPresenter = HistoryPresenter(historyRepo, router, appScreens)
}