package com.girogevoro.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.girogevoro.gitapp.domian.entities.UserEntity
import com.girogevoro.gitapp.domian.repos.UsersRepo
import com.girogevoro.gitapp.ui.navigation.ScreensApp
import com.girogevoro.gitapp.utils.mutable
import com.github.terrakok.cicerone.Router

class UsersViewModel(
    private val router: Router,
    private val screensApp: ScreensApp,
    private val usersRepo: UsersRepo,
    val recyclerPresenter: RecyclerPresenter = RecyclerPresenter(),
) : ViewModel(), UsersContract.ViewModel {

    override val updateUserListLiveData: LiveData<List<UserEntity>> = MutableLiveData()
    override val showProgressLiveData: LiveData<Boolean> = MutableLiveData()
    override val showErrorLiveData: LiveData<Throwable> = MutableLiveData()


    init {
        recyclerPresenter.setListenerClick {
            onOpenUserInfo(it)
        }
    }


    override fun getUsers() {
        showProgressLiveData.mutable().postValue(true)
        usersRepo.getUsers(
            onSuccess = {
                showProgressLiveData.mutable().postValue(false)
                recyclerPresenter.data.clear()
                recyclerPresenter.data.addAll(it)
                updateUserListLiveData.mutable().postValue(it)
            },
            onError = {
                showProgressLiveData.mutable().postValue(false)
                showErrorLiveData.mutable().postValue(it)
            }
        )
    }

    override fun getRecyclePresenter(): RecyclerUserContract.PresenterHolder {
        return recyclerPresenter
    }

    fun onBackPressed() {
        router.exit()
    }

    fun onOpenUserInfo(userEntity: UserEntity) {
        router.navigateTo(screensApp.userInfo(userEntity))
    }


}