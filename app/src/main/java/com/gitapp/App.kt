package com.girogevoro

import android.app.Application
import com.gitapp.di.AndroidModule
import com.gitapp.di.AppComponent
import com.gitapp.di.DaggerAppComponent


class App : Application() {
    companion object {
        lateinit var instance: App
    }

    val appComponent: AppComponent =
        DaggerAppComponent.builder().androidModule(AndroidModule(this)).build()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}