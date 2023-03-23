package com.girogevoro.gitapp.data.repositoty.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class GithubUserEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    @ColumnInfo(name = "login")
    val login: String,
    @ColumnInfo(name = "repos_url")
    val reposUrl: String
)