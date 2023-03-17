package com.girogevoro.gitapp.ui.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.girogevoro.App
import com.girogevoro.gitapp.databinding.FragmentUserDetailsBinding
import com.girogevoro.gitapp.domain.GithubUser
import com.girogevoro.gitapp.ui.BackButtonListener
import com.girogevoro.gitapp.utils.parcelable
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView, BackButtonListener {

    companion object {
        const val USER_DETAILS = "UserDetails"

        fun newInstance(gitHubUser: GithubUser): UserDetailsFragment {
            val args = Bundle().apply { putParcelable(USER_DETAILS, gitHubUser) }
            val fragment = UserDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val userDetailsPresenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(
            App.instance.router
        )
    }
    private var userDetailsFragmentBinding: FragmentUserDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserDetailsBinding.inflate(inflater, container, false)
        .also { userDetailsFragmentBinding = it }
        .root

    override fun onDestroy() {
        super.onDestroy()
        userDetailsFragmentBinding = null
    }

    override fun init() {
        userDetailsPresenter.user = arguments?.parcelable(USER_DETAILS)
    }

    override fun showUserLogin(login: String) {
        userDetailsFragmentBinding?.userLogin?.text = login
    }

    override fun backPressed() = userDetailsPresenter.backPressed()
}