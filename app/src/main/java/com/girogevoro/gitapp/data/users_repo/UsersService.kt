package com.girogevoro.gitapp.data.users_repo

import com.girogevoro.gitapp.domian.entities.UserEntity
import retrofit2.Call
import retrofit2.http.GET

interface UsersService {
    @GET("users")
    fun  listUsers():Call<List<UserEntity>>
}