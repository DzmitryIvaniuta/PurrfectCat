package com.example.purrfectcat.features.favouriteCats.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.purrfectcat.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ConfirmationDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val url = arguments?.getString(ARG_URL)

        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.make_a_choice))
            .setMessage(getString(R.string.download_or_delete))
            .setPositiveButton(getString(R.string.download)) { _, _ ->
                setFragmentResult(REQUEST_KEY, bundleOf(RESULT_DOWNLOAD to url))
            }
            .setNegativeButton(getString(R.string.delete)) { _, _ ->
                DeleteConfirmationDialogFragment.show(parentFragmentManager)
            }
            .create()
    }

    companion object {
        private const val ARG_URL = "url"
        private const val TAG = "ActionConfirmationDialog"
        const val REQUEST_KEY = "confirmation_request"
        const val RESULT_DOWNLOAD = "result_download"

        fun show(fragmentManager: FragmentManager, url: String) {
            val dialog = ConfirmationDialogFragment().apply {
                arguments = bundleOf(ARG_URL to url)
            }
            dialog.show(fragmentManager, TAG)
        }
    }
}
