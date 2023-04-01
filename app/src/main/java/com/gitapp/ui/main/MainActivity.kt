package com.gitapp.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import com.girogevoro.App
import com.girogevoro.gitapp.R
import com.girogevoro.gitapp.databinding.ActivityMainBinding
import com.gitapp.ui.BackButtonListener
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainContract {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private val navigator by lazy { AppNavigator(this, R.id.container) }
    private val presenter by moxyPresenter { App.instance.appComponent.getMainPresenter()}

    private var _biding:ActivityMainBinding? = null
    private val binding get() = _biding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}