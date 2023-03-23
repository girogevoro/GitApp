package com.girogevoro.gitapp.data

import com.girogevoro.gitapp.data.repositoty.local.GithubUsersRepoLocal
import com.girogevoro.gitapp.data.repositoty.web.GithubUsersRepoWeb
import com.girogevoro.gitapp.domain.GithubUser
import com.girogevoro.gitapp.domain.GithubUsersRepo
import com.girogevoro.gitapp.domain.INetworkStatus
import com.girogevoro.gitapp.domain.UserRepo
import io.reactivex.rxjava3.core.Single

class GithubUsersRepoImpl(
    private val localRepo: GithubUsersRepoLocal,
    private val webRepo: GithubUsersRepoWeb,
    private val networkStatus: INetworkStatus,
) : GithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                webRepo.getUsers().flatMap { users ->
                    Single.fromCallable {
                        localRepo.putUsers(users)
                            .subscribe()
                        users
                    }
                }
            } else {
                localRepo.getUsers()
            }
        }

    override fun getUser(login: String): Single<GithubUser> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                webRepo.getUser(login).flatMap { user ->
                    Single.fromCallable {
                        localRepo.putUser(user)
                            .subscribe()
                        user
                    }
                }
            } else {
                localRepo.getUser(login)
            }
        }

    override fun getUserRepos(user: GithubUser): Single<List<UserRepo>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                webRepo.getUserRepos(user.reposUrl).flatMap { repos ->
                    Single.fromCallable {
                        localRepo.putUserRepos(user.id, repos)
                            .subscribe()
                        repos
                    }
                }
            } else {
                localRepo.getUserRepos(user.id)
            }
        }
}