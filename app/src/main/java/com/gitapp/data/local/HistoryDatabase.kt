package com.gitapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gitapp.domain.HistoryInfoEntity

@Database(entities = [HistoryInfoEntity::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "database.db"

        fun create(context: Context): HistoryDatabase =
            Room.databaseBuilder(
                context, HistoryDatabase::class.java,
                DB_NAME
            ).build()
    }

    abstract val historyDao:HistoryDao
}
