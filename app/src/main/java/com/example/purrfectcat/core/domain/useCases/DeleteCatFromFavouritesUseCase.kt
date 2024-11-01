package com.example.purrfectcat.core.domain.useCases

interface DeleteCatFromFavouritesUseCase {

    suspend fun execute(catId: String)
}