package com.girogevoro.gitapp.ui.userInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.girogevoro.gitapp.R
import com.girogevoro.gitapp.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {
    private var _binding: ActivityUserInfoBinding? = null
    val binding:ActivityUserInfoBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}