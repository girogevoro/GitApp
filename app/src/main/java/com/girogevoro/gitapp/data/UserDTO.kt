package com.girogevoro.gitapp.data

import com.girogevoro.gitapp.domain.GithubUser
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserDTO(
    @Expose
    @SerializedName("id")
    val id: Long,
    @Expose
    @SerializedName("login")
    val login: String,
    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @Expose
    @SerializedName("repos_url")
    val reposUrl: String
) {
    fun get() = GithubUser(id, login, avatarUrl, reposUrl)
}
