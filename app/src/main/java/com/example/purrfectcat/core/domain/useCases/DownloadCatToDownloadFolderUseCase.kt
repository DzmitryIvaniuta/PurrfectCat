package com.example.purrfectcat.core.domain.useCases

interface DownloadCatToDownloadFolderUseCase {

    suspend fun execute(url: String): Long
}