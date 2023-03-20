package com.girogevoro.convertimage.ui

import android.graphics.Bitmap
import com.girogevoro.convertimage.domain.ImageConverterPNG
import com.girogevoro.convertimage.domain.ImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class MainPresenter(
    private val imageLoader: ImageLoader,
    private val imageConverterPNG: ImageConverterPNG
) :
    MvpPresenter<MainView>() {
    companion object {
        const val PATH_IN = "/storage/1BEC-2115/DCIM/Image.jpg"
        const val PATH_OUT = "/storage/1BEC-2115/DCIM/Image.png"
    }

    private val compositeDisposable = CompositeDisposable()

    fun onConvert() {
        viewState.showMessage(Messages.Loading)
        compositeDisposable.addAll(
            imageLoader.load(PATH_IN).delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        viewState.showImage(it)
                        startConvert(it)
                    },
                    {
                        viewState.showMessage(Messages.Error)
                    })
        )
    }

    private fun startConvert(it: Bitmap) {
        viewState.showMessage(Messages.Converting)
        compositeDisposable.addAll(
            imageConverterPNG.convert(it, PATH_OUT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        viewState.showLoading(it.progress)
                    }, {
                        viewState.showMessage(Messages.Error)
                    }, {
                        viewState.showMessage(Messages.Success)
                    })
        )
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
