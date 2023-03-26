package com.girogevoro

import android.app.Application
import android.util.Log
import com.girogevoro.gitapp.di.AndroidModule
import com.girogevoro.gitapp.di.AppComponent
import com.girogevoro.gitapp.di.DaggerAppComponent
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class App : Application() {
    companion object {
        lateinit var instance: App

    }

    val appComponent: AppComponent =
        DaggerAppComponent.builder().androidModule(AndroidModule(this)).build()

    override fun onCreate() {
        super.onCreate()
        instance = this

        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                // Merely log undeliverable exceptions
                Log.e("@@@", e.message ?: "")
            } else {
                // Forward all others to current thread's uncaught exception handler
                Thread.currentThread().also { thread ->
                    thread.uncaughtExceptionHandler.uncaughtException(thread, e)
                }
            }

        }
    }

}
