package com.hamzasharuf.pulse.utils.adapters.lists.publications

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamzasharuf.pulse.data.models.NewsSource

class SourcesListAdapter (private val clickListener: SourcesClickListener)
    : ListAdapter<NewsSource, RecyclerView.ViewHolder>(NewsSource.diffUtilsCallback) {

    companion object {
        private const val SOURCE_VIEW_TYPE = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SOURCE_VIEW_TYPE -> SourceViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SourceViewHolder ->{
                val item = getItem(position) as NewsSource
                holder.bind(item, clickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is NewsSource -> SOURCE_VIEW_TYPE
            else -> throw ClassCastException("Unknown item: ${getItem(position)}")
        }
    }
}