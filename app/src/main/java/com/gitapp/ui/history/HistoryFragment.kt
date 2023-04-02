package com.gitapp.ui.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.girogevoro.App
import com.girogevoro.gitapp.databinding.FragmentHistoryBinding
import com.gitapp.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class HistoryFragment() : MvpAppCompatFragment(), HistoryContract, BackButtonListener {
    companion object {
        @JvmStatic
        fun newInstance() =
            HistoryFragment()
    }

    private val presenter: HistoryPresenter by moxyPresenter {
        App.instance.appComponent.getHistoryPresenter()
    }
    var adapter: HistoryRVAdapter? = null

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener{
            backPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()

    override fun init() {
        binding.historyRecycleView.layoutManager = LinearLayoutManager(context)
        adapter = HistoryRVAdapter(presenter)
        binding.historyRecycleView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}