package com.example.purrfectcat.core.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.purrfectcat.core.data.network.paging.CatPagingSource
import com.example.purrfectcat.core.data.network.retrofit.CatApiService
import com.example.purrfectcat.core.data.storage.room.CatDatabase
import com.example.purrfectcat.core.domain.model.Cat
import com.example.purrfectcat.core.domain.repository.CatRepository
import com.example.purrfectcat.utils.androidImageLoader.AndroidImageLoader
import com.example.purrfectcat.utils.mappers.domainToCatEntity
import com.example.purrfectcat.utils.mappers.entityToCat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val catDatabase: CatDatabase,
    private val apiService: CatApiService,
    private val androidImageLoader: AndroidImageLoader
) : CatRepository {

    override suspend fun getFavouriteCats(): Flow<List<Cat>> {
        val favouriteCats = catDatabase.getCatDao().getAllCats()
        return favouriteCats.map { catEntityList -> catEntityList.map { catEntity -> catEntity.entityToCat() } }
    }

    override suspend fun saveCatToFavourites(cat: Cat) {
        val catEntity = cat.domainToCatEntity()
        catDatabase.getCatDao().insertCat(catEntity)
    }

    override suspend fun searchCats(): Flow<PagingData<Cat>> =
        Pager(config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false),
            pagingSourceFactory = { CatPagingSource(apiService) }
        ).flow

    override suspend fun deleteCat(catId: String) = catDatabase.getCatDao().deleteCat(catId)

    override suspend fun downloadCatImage(url: String): Long = androidImageLoader.downloadImage(url)
}