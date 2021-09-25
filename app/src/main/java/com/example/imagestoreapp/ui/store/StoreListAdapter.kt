package com.example.imagestoreapp.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.imagestoreapp.R
import com.example.imagestoreapp.databinding.ItemSearchImageBinding
import com.example.imagestoreapp.ui.adapter.SearchImageListViewHolder
import com.example.imagestoreapp.ui.model.ThumbnailModel
import com.example.imagestoreapp.ui.adapter.ThumbnailModelDiffCallback

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
                (getItem(viewHolder.absoluteAdapterPosition) as ThumbnailModel).isStore =
                    !(getItem(viewHolder.absoluteAdapterPosition) as ThumbnailModel).isStore
                storeClick(getItem(viewHolder.absoluteAdapterPosition) as ThumbnailModel)
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