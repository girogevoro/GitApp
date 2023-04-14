package com.gitapp.di

import com.gitapp.di.info.InfoSubcomponent
import com.gitapp.di.module.AndroidModule
import com.gitapp.di.module.CiceroneModule
import com.gitapp.di.module.InfoDialogModule
import com.gitapp.di.module.ReposModule
import com.gitapp.ui.discovery.DiscoveryPresenter
import com.gitapp.ui.history.HistoryPresenter
import com.gitapp.ui.info.InfoDialogFragmentFabric
import com.gitapp.ui.main.MainActivity
import com.gitapp.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CiceroneModule::class, InfoDialogModule::class, ReposModule::class, AndroidModule::class]
)
interface AppComponent {
    fun getInfoSubcomponent(): InfoSubcomponent

    fun inject(mainActivity: MainActivity)

    fun inject(discoveryPresenter: DiscoveryPresenter)

    fun inject(historyPresenter: HistoryPresenter)

    fun inject(mainPresenter: MainPresenter)

    fun getInfoDialogFragmentFabric(): InfoDialogFragmentFabric

}