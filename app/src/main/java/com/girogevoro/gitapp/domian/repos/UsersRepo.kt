package com.girogevoro.gitapp.domian.repos

import com.girogevoro.gitapp.domian.entities.UserEntity

interface UsersRepo {
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}