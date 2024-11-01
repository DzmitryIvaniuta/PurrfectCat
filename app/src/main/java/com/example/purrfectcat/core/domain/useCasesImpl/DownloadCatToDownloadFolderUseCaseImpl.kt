package com.example.purrfectcat.core.domain.useCasesImpl

import com.example.purrfectcat.core.domain.repository.CatRepository
import com.example.purrfectcat.core.domain.useCases.DownloadCatToDownloadFolderUseCase
import javax.inject.Inject

class DownloadCatToDownloadFolderUseCaseImpl @Inject constructor(
    private val catRepository: CatRepository
) : DownloadCatToDownloadFolderUseCase {
    override suspend fun execute(url: String): Long = catRepository.downloadCatImage(url)
}