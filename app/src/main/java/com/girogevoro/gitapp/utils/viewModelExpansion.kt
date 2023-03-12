package com.girogevoro.gitapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
    return this as? MutableLiveData<T>
        ?: throw IllegalStateException("It is not MutableLiveData")
}