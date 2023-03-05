package com.girogevoro.gitapp.ui.users

import com.girogevoro.gitapp.domian.entities.UserEntity
import com.girogevoro.gitapp.domian.repos.UsersRepo

class UsersPresenter(private val usersRepo: UsersRepo) : UsersContract.Presenter {
    var view: UsersContract.View? = null
    var inProcess:Boolean = false
    var users:List<UserEntity>? = null

    override fun attach(view: UsersContract.View) {
        this.view = view
        users?.let {view.showUsers(it)}
        view.showProgress(inProcess)
    }

    override fun detach() {
        view = null
    }

    override fun getUsers() {
        view?.showProgress(true)
        usersRepo.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUsers(it)
                inProcess = false
                users = it
            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
                inProcess = false
            }
        )
    }
}