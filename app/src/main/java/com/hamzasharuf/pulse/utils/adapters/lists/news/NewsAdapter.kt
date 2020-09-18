package com.hamzasharuf.pulse.utils.adapters.lists.news

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamzasharuf.pulse.data.models.News


class NewsAdapter(private val clickListener: NewsClickListener)
    : ListAdapter<News, RecyclerView.ViewHolder>(NewsDiffUtils()){

    companion object{
        const val ARTICLE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ARTICLE -> NewsViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is NewsViewHolder -> holder.bind(getItem(position), clickListener)
        }


    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is News -> ARTICLE
            else -> throw ClassCastException("Unknown Item ${getItem(position)}")
        }
    }

}
