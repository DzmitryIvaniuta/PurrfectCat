package com.example.purrfectcat.core.domain.useCases

import com.example.purrfectcat.core.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface GetCatsFromFavouritesUseCase {

    suspend fun execute(): Flow<List<Cat>>
}