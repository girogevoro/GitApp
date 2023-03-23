package com.girogevoro.gitapp.data.repositoty.local

import com.girogevoro.gitapp.domain.GithubUser
import com.girogevoro.gitapp.domain.UserRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepoLocal {

    fun getUsers(): Single<List<GithubUser>>
    fun putUsers(users: List<GithubUser>): Completable

    fun getUser(login: String): Single<GithubUser>
    fun putUser(user: GithubUser): Completable

    fun getUserRepos(userId: Long): Single<List<UserRepo>>
    fun putUserRepos(userId: Long, repos: List<UserRepo>): Completable
}
