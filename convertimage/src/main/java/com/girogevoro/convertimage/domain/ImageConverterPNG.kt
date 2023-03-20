package com.girogevoro.convertimage.domain

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface ImageConverterPNG {
    fun convert(image: Bitmap, pathSave:String): Observable<ConversionStatus>
}