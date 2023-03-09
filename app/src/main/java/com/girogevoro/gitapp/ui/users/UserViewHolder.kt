package com.girogevoro.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.girogevoro.gitapp.R
import com.girogevoro.gitapp.databinding.RecyclerViewUsersItemUserBinding
import com.girogevoro.gitapp.domian.entities.UserEntity

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.recycler_view_users_item_user, parent, false)
), RecyclerUserContract.ViewHolder {
    private val binding = RecyclerViewUsersItemUserBinding.bind(itemView)

    override fun showUser(userEntity: UserEntity) {
        binding.avatarImageView.load(userEntity.avatarUrl)
        binding.loginTextView.text = userEntity.login
        binding.uidTextView.text = userEntity.id.toString()
    }

    override fun getPos(): Int {
        return layoutPosition
    }

    override fun setListenerClick(f: (Int) -> Unit) {
        itemView.setOnClickListener {
            f.invoke(layoutPosition)
        }
    }
}
