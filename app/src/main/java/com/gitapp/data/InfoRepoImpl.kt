package com.gitapp.data

import com.gitapp.domain.InfoRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalDate

class InfoRepoImpl(private val infoApi: InfoApi) : InfoRepo {
    override fun getInfo(date: LocalDate): Single<String> {
        return infoApi.getInfo(date.monthValue.toString(), date.dayOfMonth.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
