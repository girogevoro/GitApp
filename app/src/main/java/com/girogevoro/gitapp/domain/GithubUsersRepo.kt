package com.girogevoro.gitapp.domain

import io.reactivex.rxjava3.core.Single

interface GithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>

    fun getUser(login: String): Single<GithubUser>

    fun getUserRepos(user: GithubUser): Single<List<UserRepo>>
}
