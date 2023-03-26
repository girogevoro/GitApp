package com.girogevoro.gitapp.di

import android.content.Context
import com.girogevoro.App
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(val context: App) {

    @Provides
    fun provideContext(): Context {
        return context
    }
}
