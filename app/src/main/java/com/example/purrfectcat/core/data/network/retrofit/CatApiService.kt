package com.example.purrfectcat.core.data.network.retrofit

import com.example.purrfectcat.core.data.network.models.CatResponseModel
import com.example.purrfectcat.utils.constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    @GET("images/search")
    suspend fun searchCats(
        @Query("limit") limit: Int = 20,
        @Query("has_breeds") hasBreeds: Int = 0,
        @Query("api_key") apiKey: String = API_KEY
    ): List<CatResponseModel>
}