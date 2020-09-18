package com.hamzasharuf.pulse.utils.adapters.lists.news

import com.hamzasharuf.pulse.data.models.News

class NewsClickListener(val clickListener: (item: News) -> Unit) {
    fun onClick(item: News) = clickListener(item)
}