package com.example.purrfectcat.utils.glideImageLoader

import android.widget.ImageView

interface GlideImageLoader {

    fun loadImage(url: String?, imageView: ImageView)
}