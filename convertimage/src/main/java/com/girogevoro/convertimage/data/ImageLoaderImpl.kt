package com.girogevoro.convertimage.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.girogevoro.convertimage.domain.ImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class ImageLoaderImpl : ImageLoader {

    override fun load(pathLoad: String): Single<Bitmap> {
        return Single.create { emitter ->
            val decodeFile = BitmapFactory.decodeFile(pathLoad)
            if (decodeFile == null) {
                emitter.onError(IllegalStateException("No load $pathLoad"))
                return@create
            }
            emitter.onSuccess(decodeFile)
        }
    }
}
