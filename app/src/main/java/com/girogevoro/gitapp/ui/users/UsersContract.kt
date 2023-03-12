package com.girogevoro.gitapp.ui.users

import androidx.lifecycle.LiveData
import com.girogevoro.gitapp.domian.entities.UserEntity

interface UsersContract {
    interface ViewModel {
        val updateUserListLiveData: LiveData<List<UserEntity>>
        val showProgressLiveData:LiveData<Boolean>
        val showErrorLiveData:LiveData<Throwable>

        fun getUsers()
        fun getRecyclePresenter(): RecyclerUserContract.PresenterHolder
    }

}