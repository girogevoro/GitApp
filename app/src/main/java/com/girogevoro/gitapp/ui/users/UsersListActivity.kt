package com.girogevoro.gitapp.ui.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.girogevoro.App
import com.girogevoro.app
import com.girogevoro.gitapp.databinding.ActivityUsersListBinding
import com.girogevoro.gitapp.domian.entities.UserEntity
import com.girogevoro.gitapp.ui.navigation.NavigatorOnlyActivity
import com.girogevoro.gitapp.ui.navigation.ScreensAppImpl


class UsersListActivity : AppCompatActivity(), UsersContract.View {
    companion object {
        fun instance(context: Context): Intent {
            return Intent(context, UsersListActivity::class.java)
        }
    }

    private var _binding: ActivityUsersListBinding? = null
    private val binding get() = _binding!!
    private val navigatorHolder = App.instance.navigatorHolder

    private val navigator = NavigatorOnlyActivity(this)

    private lateinit var presenter: UsersContract.Presenter
    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUsersListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = extractPresenter()
        presenter.attach(this)
        adapter = UsersAdapter(presenter.getRecyclePresenter())


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

    private fun extractPresenter(): UsersPresenter {
        return lastCustomNonConfigurationInstance as? UsersPresenter
            ?: UsersPresenter(App.instance.router, ScreensAppImpl(), app.usersRepo)
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

    override fun updateUserList() {
        adapter.notifyDataSetChanged()
    }


    override fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
}