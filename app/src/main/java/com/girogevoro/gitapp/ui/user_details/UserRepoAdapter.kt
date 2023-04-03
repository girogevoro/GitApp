package com.girogevoro.gitapp.ui.user_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girogevoro.gitapp.databinding.ItemRepositoriesBinding
import com.girogevoro.gitapp.domain.UserRepo

class UserRepoAdapter(private val presenter: UserDetailsPresenter) :
    RecyclerView.Adapter<UserRepoAdapter.ViewHolder>() {
    private val list = presenter.userRepoList

    inner class ViewHolder(private val binding: ItemRepositoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userRepository: UserRepo) {
            with(binding) {
                nameTextView.text = userRepository.name
                descriptionTextView.text = userRepository.description

            }
            itemView.setOnClickListener {
                presenter.openUserRepo(userRepository.htmlUrl)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRepositoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}
