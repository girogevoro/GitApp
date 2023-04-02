package com.gitapp.di

import com.gitapp.ui.discovery.DiscoveryPresenter
import com.gitapp.ui.info.InfoDialogFragment
import com.gitapp.ui.info.InfoDialogFragmentFabric
import com.gitapp.ui.info.InfoPresenter
import com.gitapp.ui.main.MainActivity
import com.gitapp.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CiceroneModule::class, MainUiModule::class, DiscoveryModule::class,
        InfoModule::class, ReposModule::class ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun getMainPresenter(): MainPresenter
    fun getDiscoveryPresenter(): DiscoveryPresenter
    fun getInfoPresenter(): InfoPresenter

    fun getInfoDialogFragmentFabric(): InfoDialogFragmentFabric

}