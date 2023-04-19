package com.gitapp.di.module

import android.content.Context
import com.gitapp.data.local.HistoryDatabase
import com.gitapp.data.local.HistoryRepoImpl
import com.gitapp.domain.HistoryRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReposModule {

    @Singleton
    @Provides
    fun provideHistoryDatabase(context: Context): HistoryDatabase =
        HistoryDatabase.create(context)

    @Singleton
    @Provides
    fun provideHistoryRepo(historyDatabase: HistoryDatabase): HistoryRepo =
        HistoryRepoImpl(historyDatabase)
}