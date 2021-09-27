package com.example.imagestoreapp.ui.common

import androidx.recyclerview.widget.DiffUtil
import com.example.imagestoreapp.ui.model.ThumbnailModel

class ThumbnailModelDiffCallback() : DiffUtil.ItemCallback<ThumbnailModel>() {
    override fun areItemsTheSame(oldItem: ThumbnailModel, newItem: ThumbnailModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ThumbnailModel, newItem: ThumbnailModel): Boolean {
        return oldItem.url == newItem.url
    }
}