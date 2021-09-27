package com.example.imagestoreapp.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.imagestoreapp.R

object ImageViewBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(ivThumbnail: ImageView, url: String) {
       Glide.with(ivThumbnail.context)
            .load(url)
            .error(R.drawable.baseline_warning_24)
            .transition(DrawableTransitionOptions().crossFade())
            .fitCenter()
            .timeout(3000)
            .into(ivThumbnail)
    }
}