package com.example.imagestoreapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import com.example.imagestoreapp.R
import com.example.imagestoreapp.databinding.ItemSearchImageBinding
import com.example.imagestoreapp.ui.adapter.SearchImageListViewHolder
import com.example.imagestoreapp.ui.model.ThumbnailModel
import com.example.imagestoreapp.ui.adapter.ThumbnailModelDiffCallback

class SearchPagingAdapter(
    private val storeClick: (ThumbnailModel) -> Unit
) : PagingDataAdapter<ThumbnailModel, SearchImageListViewHolder>(ThumbnailModelDiffCallback()) {

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
                with(getItem(viewHolder.absoluteAdapterPosition) as ThumbnailModel) {
                    isStore = !isStore
                    storeClick(this)
                }
                invalidateAll()
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SearchImageListViewHolder, position: Int) {
        getItem(position)?.let { thumbnail ->
            holder.bind(thumbnail)
        }
    }
}