package com.girogevoro.gitapp.ui.userInfo

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.girogevoro.gitapp.domian.entities.UserEntity

class UserInfoActivityContract : ActivityResultContract<UserEntity, Int>() {
    override fun createIntent(context: Context, input: UserEntity): Intent {
        return Intent(context, UserInfoActivity::class.java)
            .putExtra(UserInfoActivity.USER, input)
    }

    override fun getSynchronousResult(
        context: Context,
        input: UserEntity
    ): SynchronousResult<Int>? {
        return if (input.id == -1L) SynchronousResult(0) else null
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Int {
        return when {
            resultCode != Activity.RESULT_OK -> 0
            else -> intent?.getIntExtra(UserInfoActivity.RESULT, 0) ?: 0
        }
    }
}