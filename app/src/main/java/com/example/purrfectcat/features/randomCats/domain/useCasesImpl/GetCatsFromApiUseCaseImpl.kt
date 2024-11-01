package com.example.purrfectcat.features.randomCats.domain.useCasesImpl

import androidx.paging.PagingData
import com.example.purrfectcat.core.domain.model.Cat
import com.example.purrfectcat.core.domain.repository.CatRepository
import com.example.purrfectcat.core.domain.useCases.GetCatsFromApiUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatsFromApiUseCaseImpl @Inject constructor(
    private val catRepository: CatRepository
) : GetCatsFromApiUseCase {
    override suspend fun execute(): Flow<PagingData<Cat>> = catRepository.searchCats()
}