package com.girogevoro.gitapp.data

import com.girogevoro.gitapp.domain.UserRepo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReposDTO(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("forks_count")
    val forksCount: Int
) {
    fun get() = UserRepo(name, forksCount)
}