package com.example.purrfectcat.core.domain.useCases

import com.example.purrfectcat.core.domain.model.Cat

interface SaveCatToFavouritesUseCase {

    suspend fun execute(cat: Cat)
}