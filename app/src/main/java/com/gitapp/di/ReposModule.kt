package com.gitapp.di

import com.gitapp.data.InfoApi
import com.gitapp.data.InfoRepoImpl
import com.gitapp.domain.InfoRepo
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class ReposModule {
    @Suppress("PrivatePropertyName")
    private val BASE_URL = "http://numbersapi.com"

    @Singleton
    @Provides
    fun provideGitHubApi(): InfoApi {
        val okClient = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(InfoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideInfoRepo(infoApi: InfoApi): InfoRepo = InfoRepoImpl(infoApi)
}