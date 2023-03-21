package com.girogevoro.gitapp.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface GitHubApi {

    @GET("/users")
    fun getAllUsers(): Single<List<UserDTO>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login: String): Single<UserDTO>

    @GET()
    fun getRepos(@Url login: String): Single<List<ReposDTO>>
}
