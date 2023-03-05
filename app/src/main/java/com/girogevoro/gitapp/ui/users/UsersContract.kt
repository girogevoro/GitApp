package com.girogevoro.gitapp.ui.users

import com.girogevoro.gitapp.domian.entities.UserEntity

interface UsersContract {
    interface View{
        fun showUsers(users: List<UserEntity>)
        fun showProgress(inProgress: Boolean)
        fun showError(throwable: Throwable)
    }

    interface Presenter{
        fun attach(view: View)
        fun detach()

        fun getUsers()
    }

}