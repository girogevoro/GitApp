package com.girogevoro.gitapp.data

import android.os.Handler
import android.os.Looper
import com.girogevoro.gitapp.domian.entities.UserEntity
import com.girogevoro.gitapp.domian.repos.UsersRepo

class FakeUserRepoImpl : UsersRepo {
    companion object {
        private const val DATA_LOADING_FAKE_DELAY: Long = 1_000L
    }

    private val data: List<UserEntity> = listOf(
        UserEntity("one", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("two", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("three", 3, "https://avatars.githubusercontent.com/u/3?v=4")
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(data)
        }, DATA_LOADING_FAKE_DELAY)
    }
}