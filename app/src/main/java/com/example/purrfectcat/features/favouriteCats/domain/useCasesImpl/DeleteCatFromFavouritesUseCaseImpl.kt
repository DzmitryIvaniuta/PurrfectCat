package com.example.purrfectcat.features.favouriteCats.domain.useCasesImpl

import com.example.purrfectcat.core.domain.repository.CatRepository
import com.example.purrfectcat.core.domain.useCases.DeleteCatFromFavouritesUseCase
import javax.inject.Inject

class DeleteCatFromFavouritesUseCaseImpl @Inject constructor(
    private val catRepository: CatRepository
) : DeleteCatFromFavouritesUseCase {
    override suspend fun execute(catId: String) = catRepository.deleteCat(catId)
}