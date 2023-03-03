package com.girogevoro.gitapp.domian.entities

data class UserEntity(
    val login: String,
    val id: Long,
    var avatarUrl: String
)