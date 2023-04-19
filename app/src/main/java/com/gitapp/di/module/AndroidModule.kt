package com.gitapp.di.module

import android.content.Context
import com.girogevoro.App
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val app: App) {

    @Provides
    fun provideApp(): App = app

    @Provides
    fun provideContext(app: App): Context = app
}