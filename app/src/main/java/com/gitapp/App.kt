package com.girogevoro

import android.app.Application
import com.gitapp.di.AppComponent
import com.gitapp.di.DaggerAppComponent
import com.gitapp.di.info.InfoScopeContainer
import com.gitapp.di.info.InfoSubcomponent
import com.gitapp.di.module.AndroidModule


class App : Application(), InfoScopeContainer {
    companion object {
        lateinit var instance: App
    }

    var infoSubcomponent: InfoSubcomponent? = null
        private set

    val appComponent: AppComponent =
        DaggerAppComponent.builder().androidModule(AndroidModule(this)).build()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun initInfoSubcomponent() {
        appComponent.getInfoSubcomponent().also {
            infoSubcomponent = it
        }
    }

    override fun releaseInfoScope() {
        infoSubcomponent = null
    }
}