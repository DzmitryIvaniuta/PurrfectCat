package com.example.purrfectcat.features.randomCats.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.purrfectcat.R
import com.example.purrfectcat.app.presentation.app.App
import com.example.purrfectcat.core.domain.model.Cat
import com.example.purrfectcat.databinding.FragmentRandomCatsBinding
import com.example.purrfectcat.features.randomCats.presentation.adapter.RandomCatsPagingAdapter
import com.example.purrfectcat.features.randomCats.presentation.dialog.SaveConfirmationDialogFragment
import com.example.purrfectcat.features.randomCats.presentation.viewModel.RandomCatsViewModel
import com.example.purrfectcat.utils.bundleExtensions.getParcelableCompat
import com.example.purrfectcat.utils.glideImageLoader.GlideImageLoader
import com.example.purrfectcat.utils.viewBinding.fragmentViewBinding.viewBinding
import com.example.purrfectcat.utils.viewModelFactory.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomCatsFragment : Fragment(R.layout.fragment_random_cats) {

    private val binding by viewBinding(FragmentRandomCatsBinding::bind)

    @Inject
    lateinit var glideImageLoader: GlideImageLoader

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val randomCatsViewModel by viewModels<RandomCatsViewModel> { viewModelFactory }

    private val randomCatsPagingAdapter by lazy { RandomCatsPagingAdapter(glideImageLoader) { cat -> actionAfterClickOnItem(cat) } }

    private fun actionAfterClickOnItem(cat: Cat) {

        SaveConfirmationDialogFragment.show(
            childFragmentManager,
            cat
        )

        childFragmentManager.setFragmentResultListener(
            SaveConfirmationDialogFragment.REQUEST_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            when {
                bundle.containsKey(SaveConfirmationDialogFragment.RESULT_DOWNLOAD) -> {
                    val url = bundle.getString(SaveConfirmationDialogFragment.RESULT_DOWNLOAD)
                    url?.let { randomCatsViewModel.downloadCat(it) }
                }
                bundle.containsKey(SaveConfirmationDialogFragment.RESULT_SAVE_TO_FAVOURITES) -> {
                    val selectedCat: Cat? = bundle.getParcelableCompat(SaveConfirmationDialogFragment.RESULT_SAVE_TO_FAVOURITES)
                    selectedCat?.let { randomCatsViewModel.saveCat(it) }
                }
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
            randomCatsRecyclerView.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = randomCatsPagingAdapter
                setHasFixedSize(true)
                itemAnimator = null
            }

            viewLifecycleOwner.lifecycleScope.launch {
                randomCatsViewModel.randomCats.flowWithLifecycle(
                    lifecycle,
                    Lifecycle.State.STARTED
                ).collectLatest { pagingData ->
                    randomCatsPagingAdapter.submitData(pagingData)
                }
            }
        }
    }
}