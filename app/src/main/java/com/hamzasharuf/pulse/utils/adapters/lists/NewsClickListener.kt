package com.hamzasharuf.pulse.utils.adapters.lists

import com.hamzasharuf.pulse.data.models.Article
import javax.inject.Inject

class NewsClickListener (val clickListener: (item: Article) -> Unit) {
    fun onClick(item: Article) = clickListener(item)
}