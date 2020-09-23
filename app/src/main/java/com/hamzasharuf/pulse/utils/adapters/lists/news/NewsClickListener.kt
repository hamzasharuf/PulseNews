package com.hamzasharuf.pulse.utils.adapters.lists.news

import com.hamzasharuf.pulse.data.models.News

class NewsClickListener(val clickListener: (item: News, position: Int) -> Unit) {
    fun onClick(item: News, position: Int) = clickListener(item, position)
}