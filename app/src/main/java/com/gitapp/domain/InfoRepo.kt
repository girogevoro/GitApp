package com.gitapp.domain

import io.reactivex.rxjava3.core.Single
import java.time.LocalDate

interface InfoRepo {
    fun getInfo(date: LocalDate): Single<String>
}