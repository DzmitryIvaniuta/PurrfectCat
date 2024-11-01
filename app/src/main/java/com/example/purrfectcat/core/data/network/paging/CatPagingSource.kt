package com.example.purrfectcat.core.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.purrfectcat.core.data.network.retrofit.CatApiService
import com.example.purrfectcat.core.domain.model.Cat
import com.example.purrfectcat.utils.constants.API_KEY
import com.example.purrfectcat.utils.constants.NO
import com.example.purrfectcat.utils.mappers.responseToCat

class CatPagingSource(
    private val apiService: CatApiService,
) : PagingSource<Int, Cat>() {

    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.searchCats(
                hasBreeds = NO,
                apiKey = API_KEY,
                limit = params.loadSize
            )
            val cats = response.map { catResponseModel -> catResponseModel.responseToCat() }

            LoadResult.Page(
                data = cats,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (cats.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}