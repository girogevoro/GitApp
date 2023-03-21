package com.girogevoro.gitapp.ui.users

import com.girogevoro.gitapp.data.GithubUsersRepoImpl
import com.girogevoro.gitapp.domain.GithubUser
import com.girogevoro.gitapp.domain.GithubUsersRepo
import com.girogevoro.gitapp.ui.screens.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setGitUser(user)
        }
    }

    var disposable: Disposable? = null
    val usersListPresenter = UsersListPresenter()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.userDetail(user.login))
        }
    }

    private fun loadData() {
        disposable = usersRepo.getUsers().subscribe(
            {
                usersListPresenter.users.addAll(it)
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

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}