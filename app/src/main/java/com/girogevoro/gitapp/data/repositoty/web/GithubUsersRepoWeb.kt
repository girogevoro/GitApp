package com.girogevoro.gitapp.data.repositoty.web

import com.girogevoro.gitapp.domain.GithubUser
import com.girogevoro.gitapp.domain.UserRepo
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepoWeb {
    fun getUsers(): Single<List<GithubUser>>

    fun getUser(login: String): Single<GithubUser>

    fun getUserRepos(repoUrl: String): Single<List<UserRepo>>
}