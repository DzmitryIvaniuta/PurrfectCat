package com.example.purrfectcat.features.randomCats.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.purrfectcat.R
import com.example.purrfectcat.core.domain.model.Cat
import com.example.purrfectcat.utils.bundleExtensions.getParcelableCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SaveConfirmationDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cat: Cat? = arguments?.getParcelableCompat(ARG_CAT)

        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.make_a_choice)
            .setMessage(getString(R.string.download_or_save_to_fav))
            .setPositiveButton(R.string.download) { _, _ ->
                cat?.url?.let { url ->
                    setFragmentResult(REQUEST_KEY, bundleOf(RESULT_DOWNLOAD to url))
                }
            }
            .setNegativeButton(getString(R.string.add_to_favourites)) { _, _ ->
                cat?.let { cat ->
                    setFragmentResult(REQUEST_KEY, bundleOf(RESULT_SAVE_TO_FAVOURITES to cat))
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.added_to_favourites),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .create()
    }

    companion object {
        private const val ARG_CAT = "cat"
        private const val TAG = "SaveConfirmationDialog"
        const val REQUEST_KEY = "save_confirmation_request"
        const val RESULT_DOWNLOAD = "result_download"
        const val RESULT_SAVE_TO_FAVOURITES = "result_save_to_favourites"

        fun show(fragmentManager: FragmentManager, cat: Cat) {
            val dialog = SaveConfirmationDialogFragment().apply {
                arguments = bundleOf(ARG_CAT to cat)
            }
            dialog.show(fragmentManager, TAG)
        }
    }
}
