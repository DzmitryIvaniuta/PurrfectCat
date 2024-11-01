package com.example.purrfectcat.core.presentation.di

import com.example.purrfectcat.core.domain.useCases.DeleteCatFromFavouritesUseCase
import com.example.purrfectcat.core.domain.useCases.DownloadCatToDownloadFolderUseCase
import com.example.purrfectcat.core.domain.useCases.GetCatsFromApiUseCase
import com.example.purrfectcat.core.domain.useCases.GetCatsFromFavouritesUseCase
import com.example.purrfectcat.core.domain.useCases.SaveCatToFavouritesUseCase
import com.example.purrfectcat.core.domain.useCasesImpl.DownloadCatToDownloadFolderUseCaseImpl
import com.example.purrfectcat.features.favouriteCats.domain.useCasesImpl.DeleteCatFromFavouritesUseCaseImpl
import com.example.purrfectcat.features.favouriteCats.domain.useCasesImpl.GetCatsFromFavouritesUseCaseImpl
import com.example.purrfectcat.features.randomCats.domain.useCasesImpl.GetCatsFromApiUseCaseImpl
import com.example.purrfectcat.features.randomCats.domain.useCasesImpl.SaveCatToFavouritesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindDeleteCatFromFavouritesUseCase(
        deleteCatFromFavouritesUseCaseImpl: DeleteCatFromFavouritesUseCaseImpl
    ): DeleteCatFromFavouritesUseCase

    @Binds
    abstract fun bindDownloadCatToDownloadFolderUseCase(
        downloadCatToDownloadFolderUseCaseImpl: DownloadCatToDownloadFolderUseCaseImpl
    ): DownloadCatToDownloadFolderUseCase

    @Binds
    abstract fun bindGetCatsFromApiUseCase(
        getCatsFromApiUseCaseImpl: GetCatsFromApiUseCaseImpl
    ): GetCatsFromApiUseCase

    @Binds
    abstract fun bindGetCatsFromFavouritesUseCase(
        getCatsFromFavouritesUseCaseImpl: GetCatsFromFavouritesUseCaseImpl
    ): GetCatsFromFavouritesUseCase

    @Binds
    abstract fun bindSaveCatToFavouritesUseCase(
        saveCatToFavouritesUseCaseImpl: SaveCatToFavouritesUseCaseImpl
    ): SaveCatToFavouritesUseCase
}