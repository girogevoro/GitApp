package com.girogevoro

import android.app.Application
import com.gitapp.di.DaggerAppComponent


class App : Application() {
    companion object{
        lateinit var instance: App
    }

    val appComponent = DaggerAppComponent.builder().build()
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}