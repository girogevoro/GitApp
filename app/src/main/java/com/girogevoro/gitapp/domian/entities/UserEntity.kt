package com.girogevoro.gitapp.domian.entities


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity
    (
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    var avatarUrl: String
) : Parcelable