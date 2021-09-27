package com.example.imagestoreapp.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.imagestoreapp.R
import com.example.imagestoreapp.databinding.ItemSearchImageBinding
import com.example.imagestoreapp.ui.common.SearchImageListViewHolder
import com.example.imagestoreapp.ui.model.ThumbnailModel
import com.example.imagestoreapp.ui.common.ThumbnailModelDiffCallback

class StoreListAdapter(
    private val storeClick: (ThumbnailModel) -> Unit
) : ListAdapter<ThumbnailModel, SearchImageListViewHolder>(ThumbnailModelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageListViewHolder {
        val binding: ItemSearchImageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_search_image,
            parent,
            false
        )

        val viewHolder = SearchImageListViewHolder(binding)

        binding.apply {
            ivIsStore.setOnClickListener {
                with(currentList[viewHolder.absoluteAdapterPosition]) {
                    isStore = !isStore
                    storeClick(this)
                }
                invalidateAll()
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SearchImageListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}