package com.girogevoro.gitapp.ui.userInfo

import com.girogevoro.gitapp.domian.entities.UserEntity

interface UserInfoContract {
    interface View{
        fun showUser(userEntity: UserEntity)
    }
    interface Presenter{
        val userEntity:UserEntity?
        fun back()
    }
}