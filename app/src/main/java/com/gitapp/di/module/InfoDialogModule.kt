package com.gitapp.di.module

import com.gitapp.ui.info.InfoDialogFragmentFabric
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InfoDialogModule {

    @Singleton
    @Provides
    fun provideInfoDialogFragmentFabric(): InfoDialogFragmentFabric = InfoDialogFragmentFabric()
}