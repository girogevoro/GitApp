package com.girogevoro.gitapp.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.girogevoro.app
import com.girogevoro.gitapp.databinding.ActivityMainBinding
import com.girogevoro.gitapp.domian.entities.UserEntity
import com.girogevoro.gitapp.domian.repos.UsersRepo

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = UsersAdapter()
    private val usersRepo: UsersRepo by lazy { app.usersRepo }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        initRecyclerView()
        binding.refreshButton.setOnClickListener {
            loadData()
        }

        showProgress(false)
    }

    private fun loadData() {
        showProgress(true)
        usersRepo.getUsers(
            onSuccess = {
                showProgress(false)
                onDataLoaded(it)
            },
            onError = {
                showProgress(false)
                onError(it)
            }
        )

    }

    private fun onDataLoaded(data: List<UserEntity>) {
        adapter.setData(data)
    }

    private fun onError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }
}