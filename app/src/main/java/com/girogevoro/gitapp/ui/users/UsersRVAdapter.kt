package com.girogevoro.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girogevoro.gitapp.databinding.ItemUserBinding
import com.girogevoro.gitapp.domain.GithubUser
import com.girogevoro.gitapp.utils.loadImage

class UsersRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemUserBinding) :
        RecyclerView.ViewHolder(vb.root), UserItemView {

        override fun setGitUser(gitHunUser: GithubUser) {
            with(vb) {
                loginTextView.text = gitHunUser.login
                avatarImageView.loadImage(gitHunUser.avatarUrl)
            }
        }

        override var pos = -1
    }
}
