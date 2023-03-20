package com.girogevoro.convertimage.domain

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Single

interface ImageLoader {
    fun load(pathLoad:String): Single<Bitmap>
}