package com.girogevoro.gitapp.data.repositoty.local

import com.girogevoro.gitapp.data.repositoty.local.room.GithubDatabase
import com.girogevoro.gitapp.data.repositoty.local.room.GithubRepositoryEntity
import com.girogevoro.gitapp.data.repositoty.local.room.GithubUserEntity
import com.girogevoro.gitapp.domain.GithubUser
import com.girogevoro.gitapp.domain.UserRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepoLocalImpl(db: GithubDatabase) : GithubUsersRepoLocal {

    private val userDao = db.userDao
    private val repositoryDao = db.repositoryDao

    override fun getUsers(): Single<List<GithubUser>> {
        return userDao.getAll().map {
            mapToGithubUserList(it)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun putUsers(users: List<GithubUser>): Completable {
        return userDao.insert(mapToGithubUserEntityList(users))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUser(login: String): Single<GithubUser> {
        return userDao.findByLogin(login).map {
            mapToGithubUser(it)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun putUser(user: GithubUser): Completable {
        return userDao.insert(mapToGithubUserEntity(user))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserRepos(userId: Long): Single<List<UserRepo>> {
        return repositoryDao.findForUser(userId).map { mapToUserRepoList(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun putUserRepos(userId: Long, repos: List<UserRepo>): Completable {
        return repositoryDao.insert(mapToUserRepoEntityList(repos, userId))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun mapToGithubUser(githubUser: GithubUserEntity) = GithubUser(
        githubUser.id,
        githubUser.login,
        githubUser.avatarUrl,
        githubUser.reposUrl,
    )

    private fun mapToGithubUserEntity(githubUser: GithubUser) = GithubUserEntity(
        githubUser.id,
        githubUser.avatarUrl ?: "",
        githubUser.login,
        githubUser.reposUrl,
    )

    private fun mapToGithubUserList(users: List<GithubUserEntity>) =
        users.map {
            mapToGithubUser(it)
        }

    private fun mapToGithubUserEntityList(users: List<GithubUser>) =
        users.map {
            mapToGithubUserEntity(it)
        }


    private fun mapToUserRepoList(repos: List<GithubRepositoryEntity>) =
        repos.map {
            UserRepo(
                it.id,
                it.name,
                it.forksCount,
                it.description,
                it.htmlUrl,
            )
        }

    private fun mapToUserRepoEntityList(repos: List<UserRepo>, userId: Long) =
        repos.map {
            GithubRepositoryEntity(
                it.id,
                it.name,
                it.forksCount,
                it.description,
                it.htmlUrl,
                userId
            )
        }
}
