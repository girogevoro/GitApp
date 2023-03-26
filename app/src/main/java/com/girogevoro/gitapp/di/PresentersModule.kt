package com.girogevoro.gitapp.di

import com.girogevoro.gitapp.domain.GithubUsersRepo
import com.girogevoro.gitapp.ui.content_activity.MainPresenter
import com.girogevoro.gitapp.ui.screens.IScreens
import com.girogevoro.gitapp.ui.user_details.UserDetailsPresenter
import com.girogevoro.gitapp.ui.users.UsersPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class PresentersModule {

    @Provides
    fun providesMainPresenter(router: Router, screens: IScreens): MainPresenter {
        return MainPresenter(router, screens)
    }

    @Provides
    fun provideUserDetailsPresenter(
        router: Router,
        githubUsersRepo: GithubUsersRepo,
    ): UserDetailsPresenter {
        return UserDetailsPresenter(router, githubUsersRepo)
    }

    @Provides
    fun provideUsersPresenter(
        usersRepo: GithubUsersRepo, router: Router, screens: IScreens,
    ): UsersPresenter {
        return UsersPresenter(usersRepo, router, screens)
    }
}