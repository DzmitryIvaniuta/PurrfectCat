package com.example.purrfectcat.core.presentation.di

import android.content.Context
import com.example.purrfectcat.core.data.network.retrofit.CatApiService
import com.example.purrfectcat.core.data.repositoryImpl.CatRepositoryImpl
import com.example.purrfectcat.core.data.storage.room.CatDatabase
import com.example.purrfectcat.core.domain.repository.CatRepository
import com.example.purrfectcat.utils.constants.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class DataModule {

    @Binds
    abstract fun bindCatRepository(catRepositoryImpl: CatRepositoryImpl): CatRepository

    companion object {
        @Provides
        fun provideApiService(): CatApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(CatApiService::class.java)
        }

        @Provides
        fun provideCatDatabase(context: Context): CatDatabase {
            return CatDatabase(context)
        }
    }
}