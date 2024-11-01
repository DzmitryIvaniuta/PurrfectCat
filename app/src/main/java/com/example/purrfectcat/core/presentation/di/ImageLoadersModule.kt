package com.example.purrfectcat.core.presentation.di

import android.content.Context
import com.example.purrfectcat.utils.androidImageLoader.AndroidImageLoader
import com.example.purrfectcat.utils.glideImageLoader.GlideImageLoader
import com.example.purrfectcat.utils.glideImageLoader.GlideImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ImageLoadersModule {

    @Binds
    abstract fun bindImageLoader(glideImageLoaderImpl: GlideImageLoaderImpl): GlideImageLoader

    companion object {
        @Provides
        fun provideDownloader(context: Context): AndroidImageLoader {
            return AndroidImageLoader(context)
        }
    }
}