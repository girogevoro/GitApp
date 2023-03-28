package com.gitapp.mvp.presenter

import com.gitapp.mvp.model.Model
import com.gitapp.mvp.view.MainView

class Presenter(private val view: MainView) {
    private val model = Model()

    fun counterClick(counterNumber: CounterNumber) {
        view.setButtonText(counterNumber, model.next(counterNumber.index).toString())
    }
}

enum class CounterNumber (val index:Int){
    FIRST(0),
    SECOND(1),
    THIRD(2)
}