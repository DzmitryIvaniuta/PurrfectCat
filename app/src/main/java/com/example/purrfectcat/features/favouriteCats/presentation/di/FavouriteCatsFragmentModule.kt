package com.example.purrfectcat.features.favouriteCats.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.purrfectcat.features.favouriteCats.presentation.viewModel.FavouriteCatsViewModel
import com.example.purrfectcat.utils.viewModelFactory.ViewModelFactory
import com.example.purrfectcat.utils.viewModelFactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavouriteCatsFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteCatsViewModel::class)
    abstract fun bindViewModel(viewModel: FavouriteCatsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}