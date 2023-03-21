package com.girogevoro.gitapp.data

import com.girogevoro.gitapp.domain.GithubUser
import com.girogevoro.gitapp.domain.GithubUsersRepo
import com.girogevoro.gitapp.domain.UserRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepoImpl(private val gitHubApi: GitHubApi) : GithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        gitHubApi.getAllUsers().map {
            it.map { userDTO ->
                userDTO.get()
            }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    override fun getUser(login: String): Single<GithubUser> =
        gitHubApi.getUser(login).map {
            it.get()
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    override fun getUserRepos(login: String): Single<List<UserRepo>> =
        gitHubApi.getRepos(login).map {
            it.map { reposDTO ->
                reposDTO.get()
            }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}
