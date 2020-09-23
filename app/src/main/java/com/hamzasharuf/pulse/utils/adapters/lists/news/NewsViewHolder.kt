package com.hamzasharuf.pulse.utils.adapters.lists.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hamzasharuf.pulse.data.models.News
import com.hamzasharuf.pulse.databinding.ListItemArticleBinding

class NewsViewHolder private constructor(val binding: ListItemArticleBinding) :

    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: News, clickListener: NewsClickListener, position: Int) {
        binding.clickListener = clickListener
        binding.item = item
        binding.position = position
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): NewsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemArticleBinding.inflate(layoutInflater, parent, false)
            return NewsViewHolder(binding)
        }
    }
}