package com.girogevoro.gitapp.ui.user_details

import com.girogevoro.gitapp.domain.GithubUser
import com.girogevoro.gitapp.domain.GithubUsersRepo
import com.girogevoro.gitapp.domain.UserRepo
import com.girogevoro.gitapp.utils.disposeBy
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UserDetailsPresenter(
    private val router: Router,
    private val githubUsersRepo: GithubUsersRepo
) :
    MvpPresenter<UserDetailsView>() {

    var login: String? = null

    val userRepoList = mutableListOf<UserRepo>()

    private var currentDisposable = CompositeDisposable()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        setUser()
    }

    private fun setUser() {
        login?.let {
            githubUsersRepo.getUser(it).subscribe({ githubUser ->
                viewState.showUser(githubUser)
                setRepoList(githubUser)
            }, { throwable ->
                throwable.printStackTrace()
            }).disposeBy(currentDisposable)
        }
    }


    private fun setRepoList(githubUser: GithubUser) {
        githubUser.reposUrl.let {
            githubUsersRepo.getUserRepos(it)
                .subscribe(
                    { userRepoListIn ->
                        userRepoList.addAll(userRepoListIn)
                        viewState.updateRecyclerView()
                    },
                    { throwable -> throwable.printStackTrace() }
                ).disposeBy(currentDisposable)
        }

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        currentDisposable.dispose()
    }

    fun openUserRepo(htmlUrl: String) {
        viewState.openUserRepo(htmlUrl)
    }
}