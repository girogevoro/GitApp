package com.gitapp.ui.history

import com.gitapp.domain.HistoryInfoEntity
import com.gitapp.domain.HistoryRepo
import com.gitapp.ui.AppScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class HistoryPresenter(
    private val historyRepo: HistoryRepo,
    private val router: Router,
    private val appScreens: AppScreens
) : MvpPresenter<HistoryContract>(), InfoListPresenter {
    private val list = mutableListOf<HistoryInfoEntity>()
    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    private fun loadData() {
        disposable = historyRepo.getHistory().subscribe(
            {
                list.addAll(it)
                viewState.updateList()
            },
            {
                it.printStackTrace()
            })

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun bindView(view: InfoItemView) {
        val historyInfoEntity = list[view.pos]
        view.setTitle(historyInfoEntity.title)
        view.setDescription(historyInfoEntity.description)
    }

    override fun getCount(): Int {
        return list.size
    }
}