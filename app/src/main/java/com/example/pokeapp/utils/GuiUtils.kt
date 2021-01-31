package com.example.pokeapp.utils

import androidx.appcompat.widget.AppCompatImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

object GuiUtils {
    @JvmStatic
    fun AppCompatImageView.loadSvg(url: String) {
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(250)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }
}