package com.girogevoro.gitapp.data.repositoty.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GithubUserEntity::class, GithubRepositoryEntity::class], version = 1)
abstract class GithubDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "database.db"
        private var instance: GithubDatabase? = null

        fun getInstance(context: Context? = null): GithubDatabase = instance ?: create(context)

        private fun create(context: Context?): GithubDatabase {
            context?.also {
                instance = Room.databaseBuilder(it, GithubDatabase::class.java, DB_NAME).build()
            }
                ?: throw  RuntimeException("Database has not been created. Please call getInstance(context)")
            return instance!!
        }
    }

    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
}
