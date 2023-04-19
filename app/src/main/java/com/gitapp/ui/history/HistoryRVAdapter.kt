package com.gitapp.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girogevoro.gitapp.databinding.ItemHistoryBinding

class HistoryRVAdapter(private val presenter: InfoListPresenter) :
    RecyclerView.Adapter<HistoryRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root), InfoItemView {

        override fun setTitle(title: String) {
            binding.dataTextView.text = title
        }

        override fun setDescription(description: String) {
            binding.descriptionTextView.text = description
        }

        override var pos = -1
    }
}