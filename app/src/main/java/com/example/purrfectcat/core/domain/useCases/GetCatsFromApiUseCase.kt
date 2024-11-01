package com.example.purrfectcat.core.domain.useCases

import androidx.paging.PagingData
import com.example.purrfectcat.core.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface GetCatsFromApiUseCase {

    suspend fun execute(): Flow<PagingData<Cat>>
}