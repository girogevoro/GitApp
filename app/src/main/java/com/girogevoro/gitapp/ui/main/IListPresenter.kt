package com.girogevoro.gitapp.ui.main

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
interface IUserListPresenter : IListPresenter<UserItemView>
