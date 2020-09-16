package com.hamzasharuf.pulse.utils.adapters.lists.publications

import com.hamzasharuf.pulse.data.models.NewsSource

class SourcesClickListener (val clickListener: (item: NewsSource, position: Int) -> Unit) {
    fun onClick(item: NewsSource, position: Int) = clickListener(item, position)
}