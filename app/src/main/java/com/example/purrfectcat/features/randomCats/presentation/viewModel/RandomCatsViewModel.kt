package com.example.purrfectcat.features.randomCats.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.purrfectcat.core.domain.model.Cat
import com.example.purrfectcat.core.domain.useCases.DownloadCatToDownloadFolderUseCase
import com.example.purrfectcat.core.domain.useCases.GetCatsFromApiUseCase
import com.example.purrfectcat.core.domain.useCases.SaveCatToFavouritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomCatsViewModel @Inject constructor(
    private val getCatsFromApiUseCase: GetCatsFromApiUseCase,
    private val saveCatToFavouritesUseCase: SaveCatToFavouritesUseCase,
    private val downloadFolderUseCase: DownloadCatToDownloadFolderUseCase
): ViewModel() {

    private val _randomCats = MutableStateFlow<PagingData<Cat>>(PagingData.empty())
    val randomCats: StateFlow<PagingData<Cat>> = _randomCats

    init {
        viewModelScope.launch {
            getCatsFromApiUseCase.execute()
                .cachedIn(viewModelScope)
                .collectLatest {
                    _randomCats.value = it
                }
        }
    }

    fun downloadCat(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            downloadFolderUseCase.execute(url)
        }
    }

    fun saveCat(cat: Cat) {
        viewModelScope.launch(Dispatchers.IO) {
            saveCatToFavouritesUseCase.execute(cat)
        }
    }
}