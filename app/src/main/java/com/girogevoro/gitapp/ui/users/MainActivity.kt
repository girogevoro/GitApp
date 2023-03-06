package com.girogevoro.gitapp.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.girogevoro.app
import com.girogevoro.gitapp.databinding.ActivityMainBinding
import com.girogevoro.gitapp.domian.entities.UserEntity
import com.girogevoro.gitapp.ui.userInfo.UserInfoActivityContract


class MainActivity : AppCompatActivity(), UsersContract.View {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = UsersAdapter()

    private val activityLauncher = registerForActivityResult(UserInfoActivityContract()) {
        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
    }

    private lateinit var presenter: UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        presenter = extractPresenter()
        presenter.attach(this)
    }

    private fun extractPresenter(): UsersPresenter {
        return lastCustomNonConfigurationInstance as? UsersPresenter
            ?: UsersPresenter(app.usersRepo)
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersContract.Presenter {
        return presenter
    }

    private fun initView() {
        initRecyclerView()
        binding.refreshButton.setOnClickListener {
            presenter.getUsers()
        }

        showProgress(false)
    }


    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
    }

    override fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)
        activityLauncher.launch(users[0])
    }


    override fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
}