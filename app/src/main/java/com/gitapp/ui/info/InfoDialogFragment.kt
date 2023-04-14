package com.gitapp.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.girogevoro.App
import com.girogevoro.gitapp.R
import com.girogevoro.gitapp.databinding.DialogFragmentInfoBinding
import moxy.MvpAppCompatDialogFragment
import moxy.ktx.moxyPresenter
import java.time.LocalDate

class InfoDialogFragment : MvpAppCompatDialogFragment(), InfoView {
    companion object {
        private const val DATE = "DATE"

        fun instance(date: LocalDate): InfoDialogFragment {
            val arg = bundleOf(DATE to date)
            return InfoDialogFragment().apply { arguments = arg }
        }
    }

    private var _binding: DialogFragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter {
        InfoPresenter().apply {
            App.instance.initInfoSubcomponent()
            App.instance.infoSubcomponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentInfoBinding.inflate(inflater, container, false)
        binding.root.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun init() {
        presenter.date = arguments?.getSerializable(DATE) as LocalDate
        presenter.getInfo()
    }

    override fun showInformation(title: String, description: String) {
        binding.apply {
            loading.visibility = View.INVISIBLE
            titleTextView.visibility = View.VISIBLE
            descriptionTextView.visibility = View.VISIBLE

            titleTextView.text = title
            descriptionTextView.text = description
        }
    }

    override fun showLoading() {
        binding.apply {
            loading.visibility = View.VISIBLE
            titleTextView.visibility = View.INVISIBLE
            descriptionTextView.visibility = View.INVISIBLE
        }

    }

    override fun showError() {
        binding.apply {
            loading.visibility = View.INVISIBLE
            titleTextView.visibility = View.INVISIBLE
            descriptionTextView.visibility = View.VISIBLE

            descriptionTextView.text = getText(R.string.error)
        }
    }
}