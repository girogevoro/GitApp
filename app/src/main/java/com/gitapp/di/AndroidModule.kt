package com.gitapp.di

import android.content.Context
import com.girogevoro.App
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val context: App) {

    @Provides
    fun provideContext(): Context = context
}