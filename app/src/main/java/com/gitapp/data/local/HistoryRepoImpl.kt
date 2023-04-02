package com.gitapp.data.local

import com.gitapp.domain.HistoryInfoEntity
import com.gitapp.domain.HistoryRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class HistoryRepoImpl(private val historyDatabase: HistoryDatabase) : HistoryRepo {

    override fun getHistory(): Single<List<HistoryInfoEntity>> {
        return historyDatabase.historyDao.getAll().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun setHistory(entity: HistoryInfoEntity): Completable {
        return historyDatabase.historyDao.insert(entity).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}