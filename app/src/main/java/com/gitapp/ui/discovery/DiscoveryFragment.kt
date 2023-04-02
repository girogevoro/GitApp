package com.gitapp.ui.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girogevoro.App
import com.girogevoro.gitapp.databinding.FragmentDiscoveryBinding
import com.gitapp.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.time.LocalDate


class DiscoveryFragment : MvpAppCompatFragment(), DiscoveryContract, BackButtonListener {
    companion object {
        private const val TAG = "InfoDialogFragment"

        @JvmStatic
        fun newInstance() =
            DiscoveryFragment()
    }

    private var _binding: FragmentDiscoveryBinding? = null
    private val binding get() = _binding!!


    private val presenter: DiscoveryPresenter by moxyPresenter {
        App.instance.appComponent.getDiscoveryPresenter()
    }

    private val infoDialogFragmentFabric = App.instance.appComponent.getInfoDialogFragmentFabric()

    private var listenerKnow: ((LocalDate) -> Unit)? = null
    private var listenerHistory: (() -> Unit)? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDiscoveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideTheYear()
        binding.discoverButton.setOnClickListener {
            listenerKnow?.apply {
                val date = binding.dateDatePicker.let {
                    LocalDate.of(it.year, it.month + 1, it.dayOfMonth)
                }
                invoke(date)
            }
        }
        binding.historyFab.setOnClickListener { listenerHistory?.invoke() }
    }

    private fun hideTheYear() {
        binding.dateDatePicker.rootView.findViewById<View>(
            requireContext().resources.getIdentifier(
                "year",
                "id",
                "android"
            )
        ).visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()

    override fun setOnListenerKnow(listener: (LocalDate) -> Unit) {
        listenerKnow = listener
    }

    override fun setOnListenerHistory(listener: () -> Unit) {
        listenerHistory = listener
    }

    override fun showInfoDialog(date: LocalDate) {
        infoDialogFragmentFabric.create(date).show(parentFragmentManager, TAG)
    }
}