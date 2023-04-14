package com.gitapp.ui.info

import com.gitapp.di.info.InfoScopeContainer
import com.gitapp.domain.HistoryInfoEntity
import com.gitapp.domain.HistoryRepo
import com.gitapp.domain.InfoRepo
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import java.time.LocalDate
import javax.inject.Inject

class InfoPresenter() :
    MvpPresenter<InfoView>() {
    @Inject
    lateinit var infoRepo: InfoRepo

    @Inject
    lateinit var historyRepo: HistoryRepo

    @Inject
    lateinit var infoScopeContainer: InfoScopeContainer

    var date: LocalDate? = null
    var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

    }

    fun getInfo() {
        viewState.showLoading()
        disposable = date?.let { infoRepo.getInfo(it) }?.subscribe({

            val title = "${date?.dayOfMonth} ${date?.month}"
            viewState.showInformation(title, it)
            historyRepo.setHistory(HistoryInfoEntity(0, title, it)).subscribe()
        }, {
            viewState.showError()
        })
    }

    override fun onDestroy() {
        disposable?.dispose()
        infoScopeContainer.releaseInfoScope()
        super.onDestroy()
    }


}