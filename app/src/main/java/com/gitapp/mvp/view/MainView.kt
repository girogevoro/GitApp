package com.gitapp.mvp.view

import com.gitapp.mvp.presenter.CounterNumber

interface MainView {
    fun setButtonText(counterNumber: CounterNumber, text: String)
}

