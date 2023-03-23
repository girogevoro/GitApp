package com.girogevoro.gitapp.data.repositoty.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = GithubUserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class GithubRepositoryEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "forksCount")
    val forksCount: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "html_url")
    val htmlUrl: String,
    var userId: Long
)
