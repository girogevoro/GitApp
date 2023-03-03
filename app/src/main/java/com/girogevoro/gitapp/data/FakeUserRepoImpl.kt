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
        UserEntity("one", 1, "url1"),
        UserEntity("two", 2, "url2"),
        UserEntity("three", 3, "url3")
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(data)
        }, DATA_LOADING_FAKE_DELAY)
    }
}