package com.girogevoro.gitapp.data.repositoty.web

import com.girogevoro.gitapp.domain.GithubUser
import com.girogevoro.gitapp.domain.UserRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepoWebImpl(private val gitHubApi: GitHubApi) : GithubUsersRepoWeb {

    override fun getUsers(): Single<List<GithubUser>> =
        gitHubApi.getAllUsers().map {
            it.map { userDTO ->
                userDTO.mapToGithubUser()
            }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    override fun getUser(login: String): Single<GithubUser> =
        gitHubApi.getUser(login).map {
            it.mapToGithubUser()
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    override fun getUserRepos(repoUrl: String): Single<List<UserRepo>> =
        gitHubApi.getRepos(repoUrl).map {
            it.map { reposDTO ->
                reposDTO.mapToUserRepo()
            }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}
