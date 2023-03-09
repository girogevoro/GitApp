package com.girogevoro.gitapp.ui.userInfo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.girogevoro.gitapp.databinding.ActivityUserInfoBinding
import com.girogevoro.gitapp.domian.entities.UserEntity

class UserInfoActivity : AppCompatActivity() {
    companion object {
        const val USER = "UserInfoActivity user_entity"
        const val RESULT = "UserInfoActivity result "

        fun instance(context: Context, userEntity: UserEntity): Intent {
            return Intent(context, UserInfoActivity::class.java)
                .putExtra(USER, userEntity)
        }
    }


    private var _binding: ActivityUserInfoBinding? = null
    val binding: ActivityUserInfoBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            setResult(
                Activity.RESULT_OK,
                Intent().putExtra(RESULT, 1)
            )
            finish()
        }

        val userEntity = intent.getParcelableExtra<UserEntity>(USER)
        binding.loginTextView.text = userEntity?.login
        binding.uidTextView.text = userEntity?.id.toString()

    }
}