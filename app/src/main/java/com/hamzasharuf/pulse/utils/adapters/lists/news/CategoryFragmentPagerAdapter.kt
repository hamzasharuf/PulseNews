package com.hamzasharuf.pulse.utils.adapters.lists.news

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hamzasharuf.pulse.ui.fragments.*
import com.hamzasharuf.pulse.utils.NewsSection

class CategoryFragmentPagerAdapter(fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        NewsFragment(NewsSection.getItem(position))

    override fun getCount(): Int = NewsSection.values().size

    override fun getPageTitle(position: Int): CharSequence =
         NewsSection.getItem(position).sectionName
}