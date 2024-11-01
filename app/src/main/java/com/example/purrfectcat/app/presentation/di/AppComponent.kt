package com.example.purrfectcat.app.presentation.di

import com.example.purrfectcat.core.presentation.di.DataModule
import com.example.purrfectcat.core.presentation.di.DomainModule
import com.example.purrfectcat.core.presentation.di.ImageLoadersModule
import com.example.purrfectcat.features.favouriteCats.presentation.di.FavouriteCatsFragmentModule
import com.example.purrfectcat.features.favouriteCats.presentation.fragment.FavouriteCatsFragment
import com.example.purrfectcat.features.randomCats.presentation.di.RandomCatsFragmentModule
import com.example.purrfectcat.features.randomCats.presentation.fragment.RandomCatsFragment
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DomainModule::class,
        ImageLoadersModule::class,
        FavouriteCatsFragmentModule::class,
        RandomCatsFragmentModule::class]
)
interface AppComponent {

    fun inject(favouriteCatsFragment: FavouriteCatsFragment)

    fun inject(randomCatsFragment: RandomCatsFragment)
}