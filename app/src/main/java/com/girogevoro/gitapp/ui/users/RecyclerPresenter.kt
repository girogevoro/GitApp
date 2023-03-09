package com.girogevoro.gitapp.ui.users

import com.girogevoro.gitapp.domian.entities.UserEntity

class RecyclerPresenter : RecyclerUserContract.PresenterHolder {
    val data: MutableList<UserEntity> = mutableListOf()
    private var listener: ((UserEntity) -> Unit)? = null

    override fun bind(view: RecyclerUserContract.ViewHolder, position: Int) {
        view.showUser(data[position])
        view.setListenerClick {
            listener?.invoke(data[it])
        }
    }

    override fun count(): Int {
        return data.size
    }

    override fun setListenerClick(f: (UserEntity) -> Unit) {
        listener = f
    }
}