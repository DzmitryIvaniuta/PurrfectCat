package com.example.purrfectcat.utils.glideImageLoader

import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

class GlideImageLoaderImpl @Inject constructor(
) : GlideImageLoader {
    override fun loadImage(url: String?, imageView: ImageView) {
        Glide.with(imageView)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}