package com.girogevoro.gitapp.domian.entities

import com.google.gson.annotations.SerializedName

data class UserEntity(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    var avatarUrl: String
)