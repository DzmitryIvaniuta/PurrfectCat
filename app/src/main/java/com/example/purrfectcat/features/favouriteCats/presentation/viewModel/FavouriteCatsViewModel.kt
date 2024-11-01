package com.example.purrfectcat.features.favouriteCats.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.purrfectcat.core.domain.model.Cat
import com.example.purrfectcat.core.domain.useCases.DeleteCatFromFavouritesUseCase
import com.example.purrfectcat.core.domain.useCases.DownloadCatToDownloadFolderUseCase
import com.example.purrfectcat.core.domain.useCases.GetCatsFromFavouritesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteCatsViewModel @Inject constructor(
    private val deleteCatFromFavouritesUseCase: DeleteCatFromFavouritesUseCase,
    private val getCatsFromFavouritesUseCase: GetCatsFromFavouritesUseCase,
    private val downloadFolderUseCase: DownloadCatToDownloadFolderUseCase
) : ViewModel() {

    private val _favouriteCats = MutableStateFlow<List<Cat>>(emptyList())
    val favouriteCats: StateFlow<List<Cat>> = _favouriteCats

    init {
        getFavoriteCats()
    }

    private fun getFavoriteCats() {
        viewModelScope.launch {
            getCatsFromFavouritesUseCase.execute()
                .collectLatest { catList ->
                _favouriteCats.update { catList }
            }
        }
    }

    fun downloadCat(url: String) {
        viewModelScope.launch {
            downloadFolderUseCase.execute(url)
        }
    }

    fun deleteCat(catId: String) {
        viewModelScope.launch {
            deleteCatFromFavouritesUseCase.execute(catId)
        }
    }
}