package com.girogevoro.convertimage.data

import android.graphics.Bitmap
import com.girogevoro.convertimage.domain.ConversionStatus
import com.girogevoro.convertimage.domain.ImageConverterPNG
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.FileOutputStream

class ImageConverterPNGImpl : ImageConverterPNG {
    companion object {
        const val MAX_PERCENT = 100
        const val DELAY = 30L
        const val PNG_QUANTITY = 90
    }

    override fun convert(image: Bitmap, pathSave: String): Observable<ConversionStatus> {
        return Observable.create<ConversionStatus> { emitter ->
            (0..MAX_PERCENT).forEach {
                emitter.onNext(ConversionStatus(it))
                Thread.sleep(DELAY)
            }

            val convertResult = image.compress(
                Bitmap.CompressFormat.PNG,
                PNG_QUANTITY,
                FileOutputStream(pathSave)
            )

            if (!convertResult) {
                emitter.onError(IllegalArgumentException("conversion failure"))
                return@create
            }

            emitter.onComplete()
        }
    }
}