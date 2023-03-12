package com.girogevoro.gitapp.ui.users

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.girogevoro.App
import com.girogevoro.app
import com.girogevoro.gitapp.data.users_repo.UsersRepoImpl
import com.girogevoro.gitapp.databinding.ActivityUsersListBinding
import com.girogevoro.gitapp.ui.navigation.NavigatorOnlyActivity
import com.girogevoro.gitapp.ui.navigation.ScreensAppImpl
import com.girogevoro.gitapp.utils.viewModelProviderFactoryOf


class UsersListActivity : AppCompatActivity() {
    companion object {
        fun instance(context: Context): Intent {
            return Intent(context, UsersListActivity::class.java)
        }
    }

    private var _binding: ActivityUsersListBinding? = null
    private val binding get() = _binding!!
    private val navigatorHolder = App.instance.navigatorHolder

    private val navigator = NavigatorOnlyActivity(this)

    private lateinit var adapter: UsersAdapter

    private val usersViewModel: UsersViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactoryOf {
            return@viewModelProviderFactoryOf UsersViewModel(
                app.router,
                ScreensAppImpl(),
                UsersRepoImpl()
            )
        })[UsersViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUsersListBinding.inflate(layoutInflater)
        setContentView(binding.root)



        adapter = UsersAdapter(usersViewModel.getRecyclePresenter())


        initView()

    }


    override fun onResume() {
        navigatorHolder.setNavigator(navigator)
        super.onResume()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun initView() {
        initRecyclerView()
        binding.refreshButton.setOnClickListener {
            usersViewModel.getUsers()
        }


        usersViewModel.updateUserListLiveData.observe(this){
            updateUserList()
        }

        usersViewModel.showProgressLiveData.observe(this){
            showProgress(it)
        }

        usersViewModel.showErrorLiveData.observe(this){
            showError(it)
        }


        showProgress(false)
    }


    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateUserList() {
        adapter.notifyDataSetChanged()
    }


    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
}