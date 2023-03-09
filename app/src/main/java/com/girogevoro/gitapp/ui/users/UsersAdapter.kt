package com.girogevoro.gitapp.ui.users

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter(private val presenter: RecyclerUserContract.PresenterHolder) :
    RecyclerView.Adapter<UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(parent)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        presenter.bind(holder, position)
    }

    override fun getItemCount(): Int = presenter.count()
}