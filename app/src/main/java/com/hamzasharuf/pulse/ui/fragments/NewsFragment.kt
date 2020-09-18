package com.hamzasharuf.pulse.ui.fragments

import com.hamzasharuf.pulse.utils.NewsSection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment(val section: NewsSection) : BaseArticlesFragment() {

    override fun loadData(isRefreshing: Boolean) {
        if (isRefreshing || !viewModel.isNewsAvailable)
            viewModel.getNews(section)
    }

}