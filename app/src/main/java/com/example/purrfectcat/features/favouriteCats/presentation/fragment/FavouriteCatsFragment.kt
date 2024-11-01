package com.example.purrfectcat.features.favouriteCats.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.purrfectcat.R
import com.example.purrfectcat.app.presentation.app.App
import com.example.purrfectcat.core.domain.model.Cat
import com.example.purrfectcat.databinding.FragmentFavouriteCatsBinding
import com.example.purrfectcat.features.favouriteCats.presentation.adapter.FavouriteCatsAdapter
import com.example.purrfectcat.features.favouriteCats.presentation.dialog.ConfirmationDialogFragment
import com.example.purrfectcat.features.favouriteCats.presentation.dialog.DeleteConfirmationDialogFragment
import com.example.purrfectcat.features.favouriteCats.presentation.viewModel.FavouriteCatsViewModel
import com.example.purrfectcat.utils.glideImageLoader.GlideImageLoader
import com.example.purrfectcat.utils.viewBinding.fragmentViewBinding.viewBinding
import com.example.purrfectcat.utils.viewModelFactory.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject


class FavouriteCatsFragment : Fragment(R.layout.fragment_favourite_cats) {

    private val binding by viewBinding(FragmentFavouriteCatsBinding::bind)

    @Inject
    lateinit var glideImageLoader: GlideImageLoader

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val favouriteCatsViewModel by viewModels<FavouriteCatsViewModel> { viewModelFactory }

    private val favouriteCatsPagingAdapter by lazy { FavouriteCatsAdapter(glideImageLoader) { cat -> actionAfterClickOnItem(cat) } }

    private fun actionAfterClickOnItem(cat: Cat) {

        ConfirmationDialogFragment.show(
            childFragmentManager,
            cat.url
        )

        childFragmentManager.setFragmentResultListener(
            ConfirmationDialogFragment.REQUEST_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            if (bundle.containsKey(ConfirmationDialogFragment.RESULT_DOWNLOAD)) {
                val url = bundle.getString(ConfirmationDialogFragment.RESULT_DOWNLOAD)
                url?.let { favouriteCatsViewModel.downloadCat(it) }
            }
        }

        childFragmentManager.setFragmentResultListener(
            DeleteConfirmationDialogFragment.REQUEST_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            if (bundle.getBoolean(DeleteConfirmationDialogFragment.RESULT_DELETE)) {
                favouriteCatsViewModel.deleteCat(cat.id)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? App)?.appComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateView(inflater, container, savedInstanceState)
            ?: throw IllegalStateException("View cannot be null")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        binding.apply {
            favouriteCatsRecyclerView.layoutManager = LinearLayoutManager(context)
            favouriteCatsRecyclerView.adapter = favouriteCatsPagingAdapter

            viewLifecycleOwner.lifecycleScope.launch {
                favouriteCatsViewModel.favouriteCats.flowWithLifecycle(
                    lifecycle,
                    Lifecycle.State.STARTED
                ).collect { catsList ->
                    favouriteCatsPagingAdapter.differ.submitList(catsList)
                }
            }
        }
    }
}