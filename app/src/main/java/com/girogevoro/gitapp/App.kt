package com.girogevoro

import android.app.Application
import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.girogevoro.gitapp.data.GitHubApi
import com.girogevoro.gitapp.data.GithubUsersRepoImpl
import com.girogevoro.gitapp.domain.GithubUsersRepo
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    companion object {
        lateinit var instance: App
        private const val BASE_URL = "https://api.github.com"
    }

    //Временно до даггера положим это тут
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

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

    //okhttp3
    private val okClient = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    private fun createGson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    val gitHubApi: GitHubApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .build()
            .create(GitHubApi::class.java)
    }

    val githubUsersRepo: GithubUsersRepo by lazy {
        GithubUsersRepoImpl(instance.gitHubApi)
    }
}
