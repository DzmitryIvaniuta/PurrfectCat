package com.example.purrfectcat.features.favouriteCats.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.purrfectcat.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DeleteConfirmationDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.deleting))
            .setMessage(getString(R.string.are_you_sure))
            .setPositiveButton(R.string.delete) { _, _ ->
                setFragmentResult(REQUEST_KEY, bundleOf(RESULT_DELETE to true))
                Toast.makeText(requireContext(), getString(R.string.deleted), Toast.LENGTH_SHORT)
                    .show()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    companion object {
        private const val TAG = "DeleteConfirmationDialog"
        const val REQUEST_KEY = "delete_request"
        const val RESULT_DELETE = "result_delete"

        fun show(fragmentManager: FragmentManager) {
            val dialog = DeleteConfirmationDialogFragment()
            dialog.show(fragmentManager, TAG)
        }
    }
}
