package com.example.purrfectcat.core.domain.repository

import androidx.paging.PagingData
import com.example.purrfectcat.core.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatRepository {

    suspend fun getFavouriteCats(): Flow<List<Cat>>

    suspend fun saveCatToFavourites(cat: Cat)

    suspend fun searchCats(): Flow<PagingData<Cat>>

    suspend fun deleteCat(catId: String)

    suspend fun downloadCatImage(url: String): Long
}