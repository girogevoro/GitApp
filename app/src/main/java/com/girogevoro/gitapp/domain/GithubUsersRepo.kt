package com.girogevoro.gitapp.domain

import io.reactivex.rxjava3.core.Single

interface GithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>

    fun getUserById(login: String): Single<GithubUser>

    fun getUserRepos(login: String): Single<List<UserRepo>>
}
