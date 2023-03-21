package com.girogevoro.gitapp.utils

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.girogevoro.gitapp.R
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(com.google.android.material.R.drawable.abc_btn_check_material)
        .circleCrop()
        .into(this)
}

fun Disposable.disposeBy(bag: CompositeDisposable){
    bag.add(this)
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}
