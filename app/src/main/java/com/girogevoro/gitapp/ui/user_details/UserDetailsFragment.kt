package com.girogevoro.gitapp.ui.user_details

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.girogevoro.App
import com.girogevoro.gitapp.databinding.FragmentUserDetailsBinding
import com.girogevoro.gitapp.domain.GithubUser
import com.girogevoro.gitapp.ui.BackButtonListener
import com.girogevoro.gitapp.utils.loadImage
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView, BackButtonListener {

    companion object {
        const val USER_LOGIN = "UserDetails"

        fun newInstance(login: String): UserDetailsFragment {
            val args = Bundle().apply { putString(USER_LOGIN, login) }
            val fragment = UserDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val userDetailsPresenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(
            App.instance.router,
            App.instance.githubUsersRepo
        )
    }
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    private val userRepoAdapter: UserRepoAdapter by lazy {
        UserRepoAdapter(userDetailsPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserDetailsBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.repositoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userRepoAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun init() {
        userDetailsPresenter.login = arguments?.getString(USER_LOGIN)
    }

    override fun showUser(githubUser: GithubUser) {
        binding.loginTextView.text = githubUser.login
        binding.picImageView.loadImage(githubUser.avatarUrl)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateRecyclerView() {
        userRepoAdapter.notifyDataSetChanged()
    }

    override fun openUserRepo(repoUrl: String) {
        startActivity(Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(repoUrl)
        })
    }

    override fun backPressed() = userDetailsPresenter.backPressed()
}