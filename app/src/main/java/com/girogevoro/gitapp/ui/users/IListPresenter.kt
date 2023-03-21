package com.girogevoro.gitapp.ui.users

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
interface IUserListPresenter : IListPresenter<UserItemView>
