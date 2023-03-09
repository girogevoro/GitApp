package com.girogevoro.gitapp.ui.users

import com.girogevoro.gitapp.domian.entities.UserEntity

interface RecyclerUserContract {
    interface ViewHolder {
        fun showUser(userEntity: UserEntity)
        fun getPos():Int
        fun setListenerClick(f: (position:Int) -> Unit)

    }

    interface PresenterHolder {
        fun bind(view: ViewHolder, position: Int)
        fun count(): Int
        fun setListenerClick(f: (UserEntity) -> Unit)
    }
}