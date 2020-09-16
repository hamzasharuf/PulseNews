package com.hamzasharuf.pulse.utils.adapters.lists.publications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hamzasharuf.pulse.data.models.NewsSource
import com.hamzasharuf.pulse.databinding.ListItemSourceBinding

class SourceViewHolder(val binding: ListItemSourceBinding) : RecyclerView.ViewHolder(binding.root) {

    val cachedSourcesList = mutableListOf<NewsSource>()


    fun bind(item: NewsSource, clickListener: SourcesClickListener){
//        item.position = adapterPosition
        binding.item = item
        binding.clickListener = clickListener
        binding.position = adapterPosition
        binding.executePendingBindings()
    }

    companion object{

        fun from(parent: ViewGroup): SourceViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemSourceBinding.inflate(layoutInflater, parent, false)
            return SourceViewHolder(binding)
        }

    }

}
