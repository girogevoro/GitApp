package com.gitapp.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface InfoApi {
    @GET("/{month}/{day}/date")
    fun getInfo(@Path("month") month: String, @Path("day") day: String): Single<String>
}