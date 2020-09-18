package com.hamzasharuf.pulse.ui.fragments

import com.hamzasharuf.pulse.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportFragment : BaseArticlesFragment() {

    override fun loadData(isRefreshing: Boolean) {
        if (isRefreshing || !viewModel.isNewsAvailable)
            viewModel.getAllNews(Constants.SPORT_SECTION)
    }

}