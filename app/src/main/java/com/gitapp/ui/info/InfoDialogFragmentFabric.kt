package com.gitapp.ui.info

import java.time.LocalDate

class InfoDialogFragmentFabric {
    fun create(date: LocalDate): InfoDialogFragment = InfoDialogFragment.instance(date)
}