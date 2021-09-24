package com.example.imagestoreapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imagestoreapp.R
import com.example.imagestoreapp.databinding.ItemSearchImageBinding
import com.example.imagestoreapp.ui.model.ThumbnailModel

class SearchPagingAdapter(
        private val imageItemClick: (ThumbnailModel) -> Unit
): PagingDataAdapter<ThumbnailModel, SearchPagingAdapter.SearchImageListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageListViewHolder {
        val binding: ItemSearchImageBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_search_image, parent, false)
        val viewHolder = SearchImageListViewHolder(binding)

        binding.apply {
            root.setOnClickListener {
                imageItemClick(getItem(viewHolder.absoluteAdapterPosition) as ThumbnailModel)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SearchImageListViewHolder, position: Int) {
        getItem(position)?.let { thumbnail ->
            holder.bind(thumbnail)
        }
    }

    inner class SearchImageListViewHolder(private val binding: ItemSearchImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(thumbnail: ThumbnailModel) {
            binding.thumbnail = thumbnail
            binding.executePendingBindings()
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ThumbnailModel>() {
            override fun areItemsTheSame(oldItem: ThumbnailModel, newItem: ThumbnailModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ThumbnailModel, newItem: ThumbnailModel): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }
}