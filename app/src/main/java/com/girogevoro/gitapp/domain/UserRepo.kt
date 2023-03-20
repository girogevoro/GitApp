package com.girogevoro.gitapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepo(
    val name: String,
    val forksCount: Int
) : Parcelable
