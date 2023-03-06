package com.girogevoro.gitapp.ui.userInfo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.girogevoro.gitapp.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {
    companion object {
        const val USER = "UserInfoActivity user_entity"
        const val RESULT = "UserInfoActivity result "
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
    }
}