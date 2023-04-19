package com.gitapp.domain

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface HistoryRepo {
    fun getHistory(): Single<List<HistoryInfoEntity>>
    fun setHistory(entity: HistoryInfoEntity):Completable
}