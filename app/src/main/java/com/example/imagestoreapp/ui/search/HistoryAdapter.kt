package com.example.imagestoreapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagestoreapp.R
import com.example.imagestoreapp.data.database.entity.History
import com.example.imagestoreapp.databinding.ItemHistoryBinding

class HistoryAdapter(
    private var historyItemClick: (History) -> Unit,
    private var historyItemDelete: (History) -> Unit
) : ListAdapter<History, HistoryAdapter.HistoryViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding: ItemHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_history,
            parent,
            false
        )

        val viewHolder = HistoryViewHolder(binding)

        binding.apply {
            root.setOnClickListener {
                historyItemClick(getItem(viewHolder.absoluteAdapterPosition))
            }
            ivDeleteHistory.setOnClickListener {
                historyItemDelete(getItem(viewHolder.absoluteAdapterPosition))
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(history: History) {
            binding.history = history
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<History>() {
            override fun areContentsTheSame(oldItem: History, newItem: History) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: History, newItem: History) =
                oldItem.keyword == newItem.keyword
        }
    }
}