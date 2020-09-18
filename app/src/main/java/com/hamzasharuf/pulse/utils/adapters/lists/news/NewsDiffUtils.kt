package com.hamzasharuf.pulse.utils.adapters.lists.news

import androidx.recyclerview.widget.DiffUtil
import com.hamzasharuf.pulse.data.models.News

class NewsDiffUtils: DiffUtil.ItemCallback<News>(){

    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem.url.equals(newItem.url)

}