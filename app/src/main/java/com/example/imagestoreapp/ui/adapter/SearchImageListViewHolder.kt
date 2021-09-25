package com.example.imagestoreapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.imagestoreapp.databinding.ItemSearchImageBinding
import com.example.imagestoreapp.ui.model.ThumbnailModel

class SearchImageListViewHolder(private val binding: ItemSearchImageBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(thumbnail: ThumbnailModel) {
        binding.thumbnail = thumbnail
        binding.executePendingBindings()
    }
}