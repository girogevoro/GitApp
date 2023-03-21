package com.girogevoro.gitapp.ui.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.girogevoro.gitapp.R
import com.girogevoro.gitapp.databinding.DialogForksCountBinding

class ForksCounterDialogFragment(private val forksCount: Int) : DialogFragment() {

    private var _viewBinding: DialogForksCountBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DialogForksCountBinding.inflate(inflater, container, false).also {
            _viewBinding = it
        }.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(viewBinding) {
            forksCountTv.text = getString(R.string.number_of_forks, forksCount)
            root.setOnClickListener {
                dismiss()
            }
        }
    }
}