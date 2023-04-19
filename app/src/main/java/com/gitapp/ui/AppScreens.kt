package com.gitapp.ui

import com.gitapp.ui.discovery.DiscoveryFragment
import com.gitapp.ui.history.HistoryFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AppScreens {
    fun discovery() = FragmentScreen { DiscoveryFragment.newInstance() }
    fun history() = FragmentScreen { HistoryFragment.newInstance() }
}