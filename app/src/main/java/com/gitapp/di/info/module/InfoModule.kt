package com.gitapp.di.info.module

import com.girogevoro.App
import com.gitapp.data.InfoApi
import com.gitapp.data.InfoRepoImpl
import com.gitapp.di.info.InfoScope
import com.gitapp.di.info.InfoScopeContainer
import com.gitapp.domain.InfoRepo
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Named

@Module
class InfoModule {

    @Named("base_url")
    @Provides
    fun provideBaseUrl(): String = "http://numbersapi.com"

    @InfoScope
    @Provides
    fun provideGitHubApi(@Named("base_url") baseUrl: String): InfoApi {
        val okClient = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(InfoApi::class.java)
    }

    @InfoScope
    @Provides
    fun provideInfoRepo(infoApi: InfoApi): InfoRepo = InfoRepoImpl(infoApi)

    @Provides
    fun provideInfoScopeContainer(app: App): InfoScopeContainer = app
}