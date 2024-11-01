package com.example.purrfectcat.features.favouriteCats.domain.useCasesImpl

import com.example.purrfectcat.core.domain.model.Cat
import com.example.purrfectcat.core.domain.repository.CatRepository
import com.example.purrfectcat.core.domain.useCases.GetCatsFromFavouritesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatsFromFavouritesUseCaseImpl @Inject constructor(
    private val catRepository: CatRepository
) : GetCatsFromFavouritesUseCase {
    override suspend fun execute(): Flow<List<Cat>> = catRepository.getFavouriteCats()
}