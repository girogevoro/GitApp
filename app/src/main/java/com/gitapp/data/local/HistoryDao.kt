package com.gitapp.data.local

import androidx.appcompat.widget.AppCompatTextView
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gitapp.domain.HistoryInfoEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface HistoryDao {
    @Insert
    fun insert(info:HistoryInfoEntity):Completable

    @Query("SELECT * FROM HistoryInfoEntity")
    fun getAll():Single<List<HistoryInfoEntity>>
}