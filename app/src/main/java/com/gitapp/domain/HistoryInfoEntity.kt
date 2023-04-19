package com.gitapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class HistoryInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String
)

