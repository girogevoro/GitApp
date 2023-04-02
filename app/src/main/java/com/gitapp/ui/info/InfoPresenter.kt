package com.gitapp.ui.info

import com.gitapp.domain.InfoRepo
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import java.time.LocalDate

class InfoPresenter(private val infoRepo: InfoRepo) : MvpPresenter<InfoContract>() {
    var date: LocalDate? = null
    var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

    }

    fun getInfo(){
        disposable = date?.let { infoRepo.getInfo(it) }?.subscribe({
            viewState.show("${date?.dayOfMonth} ${date?.month}", it)
        }, {
            viewState.show("${date?.dayOfMonth} ${date?.month}", "...")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }


}