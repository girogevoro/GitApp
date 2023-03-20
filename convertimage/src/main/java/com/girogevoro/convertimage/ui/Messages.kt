package com.girogevoro.convertimage.ui

import com.girogevoro.convertimage.R

sealed class Messages(val id: Int) {
    object Error : Messages(R.string.message_error)
    object Success : Messages(R.string.message_success)
    object Converting : Messages(R.string.message_converting)
    object Loading : Messages(R.string.message_loading)
}