package com.gitapp.ui.history

interface ListPresenter<V : ItemView> {
    fun bindView(view: V)
    fun getCount(): Int
}

interface InfoListPresenter : ListPresenter<InfoItemView>
