package com.girogevoro.gitapp.ui.users

import com.girogevoro.gitapp.domian.entities.UserEntity
import com.girogevoro.gitapp.domian.repos.UsersRepo
import com.girogevoro.gitapp.ui.navigation.ScreensApp
import com.github.terrakok.cicerone.Router

class UsersPresenter(
    private val router: Router,
    private val screensApp: ScreensApp,
    private val usersRepo: UsersRepo,
    val recyclerPresenter: RecyclerPresenter = RecyclerPresenter()
) : UsersContract.Presenter {
    var view: UsersContract.View? = null
    var inProcess: Boolean = false
    var users: List<UserEntity>? = null

    init {
        recyclerPresenter.setListenerClick {
            onOpenUserInfo(it)
        }
    }


    override fun attach(view: UsersContract.View) {
        this.view = view
        users?.let {
            recyclerPresenter.data.clear()
            recyclerPresenter.data.addAll(users!!)
        }
        view.showProgress(inProcess)
    }

    override fun detach() {
        view = null
    }

    override fun getUsers() {
        view?.showProgress(true)
        usersRepo.getUsers(
            onSuccess = {
                view?.showProgress(false)
                recyclerPresenter.data.clear()
                recyclerPresenter.data.addAll(it)
                inProcess = false
                users = it
            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
                inProcess = false
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