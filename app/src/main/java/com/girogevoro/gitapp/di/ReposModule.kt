package com.girogevoro.gitapp.di

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.girogevoro.App
import com.girogevoro.gitapp.data.GithubUsersRepoImpl
import com.girogevoro.gitapp.data.NetworkStatusImpl
import com.girogevoro.gitapp.data.repositoty.local.GithubUsersRepoLocal
import com.girogevoro.gitapp.data.repositoty.local.GithubUsersRepoLocalImpl
import com.girogevoro.gitapp.data.repositoty.local.room.GithubDatabase
import com.girogevoro.gitapp.data.repositoty.web.GitHubApi
import com.girogevoro.gitapp.data.repositoty.web.GithubUsersRepoWeb
import com.girogevoro.gitapp.data.repositoty.web.GithubUsersRepoWebImpl
import com.girogevoro.gitapp.domain.GithubUsersRepo
import com.girogevoro.gitapp.domain.INetworkStatus
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ReposModule {
    private val BASE_URL = "https://api.github.com"

    @Singleton
    @Provides
    fun provideGithubUsersRepo(
        localRepo: GithubUsersRepoLocal,
        webRepo: GithubUsersRepoWeb,
        networkStatus: INetworkStatus,
    ): GithubUsersRepo {
        return GithubUsersRepoImpl(localRepo, webRepo, networkStatus)
    }

    @Singleton
    @Provides
    fun provideGithubUsersRepoLocal(db: GithubDatabase): GithubUsersRepoLocal {
        return GithubUsersRepoLocalImpl(db)
    }

    @Singleton
    @Provides
    fun provideGithubDatabase(context: Context): GithubDatabase {
        return GithubDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideGithubUsersRepoWeb(gitHubApi: GitHubApi): GithubUsersRepoWeb {
        return GithubUsersRepoWebImpl(gitHubApi)
    }

    @Singleton
    @Provides
    fun provideGitHubApi():GitHubApi{
         val okClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

         val createGson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(createGson))
                .build()
                .create(GitHubApi::class.java)

    }

    @Singleton
    @Provides
    fun provideNetworkStatus(context: Context):INetworkStatus{
        return NetworkStatusImpl(context)
    }


}