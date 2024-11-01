package com.example.purrfectcat.features.randomCats.domain.useCasesImpl

import com.example.purrfectcat.core.domain.model.Cat
import com.example.purrfectcat.core.domain.repository.CatRepository
import com.example.purrfectcat.core.domain.useCases.SaveCatToFavouritesUseCase
import javax.inject.Inject

class SaveCatToFavouritesUseCaseImpl @Inject constructor(
    private val catRepository: CatRepository
) : SaveCatToFavouritesUseCase {
    override suspend fun execute(cat: Cat) = catRepository.saveCatToFavourites(cat)
}