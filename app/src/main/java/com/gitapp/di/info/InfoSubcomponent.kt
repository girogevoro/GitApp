package com.gitapp.di.info

import com.gitapp.di.info.module.InfoModule
import com.gitapp.ui.info.InfoPresenter
import dagger.Subcomponent

@InfoScope
@Subcomponent(
    modules = [
        InfoModule::class
    ]
)
interface InfoSubcomponent {
    fun inject(infoPresenter: InfoPresenter)
}