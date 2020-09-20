package com.hamzasharuf.pulse.utils.adapters.lists.news

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hamzasharuf.pulse.data.models.NewsSection
import com.hamzasharuf.pulse.ui.fragments.news.NewsFragment

class CategoryFragmentPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = NewsSection.values().size

    override fun createFragment(position: Int): Fragment = NewsFragment(NewsSection.getItem(position))

}