package com.example.purrfectcat.features.randomCats.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.purrfectcat.features.randomCats.presentation.viewModel.RandomCatsViewModel
import com.example.purrfectcat.utils.viewModelFactory.ViewModelFactory
import com.example.purrfectcat.utils.viewModelFactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RandomCatsFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(RandomCatsViewModel::class)
    abstract fun bindViewModel(viewModel: RandomCatsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}