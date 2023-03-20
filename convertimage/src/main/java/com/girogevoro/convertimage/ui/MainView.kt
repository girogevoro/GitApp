package com.girogevoro.convertimage.ui

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun init()
    fun showLoading(progress: Int)
    fun showMessage(message:Messages)
    fun showImage(imageBitmap: Bitmap)
}

