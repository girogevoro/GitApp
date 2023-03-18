package com.girogevoro.gitapp.data

import com.girogevoro.gitapp.domain.GithubUser
import io.reactivex.rxjava3.core.Single

class GithubUsersRepo {
    private val repositories = Single.just(
        listOf(
            GithubUser("login1"),
            GithubUser("login2"),
            GithubUser("login3"),
            GithubUser("login4"),
            GithubUser("login5")
        )
    )

    fun getUsers(): Single<List<GithubUser>> {
        return repositories
    }
}