package com.hamzasharuf.pulse.utils.adapters.lists.news

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.ui.fragments.BusinessFragment
import com.hamzasharuf.pulse.ui.fragments.CultureFragment
import com.hamzasharuf.pulse.ui.fragments.EnvironmentFragment
import com.hamzasharuf.pulse.ui.fragments.FashionFragment
import com.hamzasharuf.pulse.ui.fragments.HomeFragment
import com.hamzasharuf.pulse.ui.fragments.ScienceFragment
import com.hamzasharuf.pulse.ui.fragments.SocietyFragment
import com.hamzasharuf.pulse.ui.fragments.SportFragment
import com.hamzasharuf.pulse.ui.fragments.WorldFragment
import com.hamzasharuf.pulse.utils.Constants
import java.lang.IllegalArgumentException


class CategoryFragmentPagerAdapter(
    private val mContext: Context, fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            Constants.HOME -> HomeFragment()
            Constants.WORLD -> WorldFragment()
            Constants.SCIENCE -> ScienceFragment()
            Constants.SPORT -> SportFragment()
            Constants.ENVIRONMENT -> EnvironmentFragment()
            Constants.SOCIETY -> SocietyFragment()
            Constants.FASHION -> FashionFragment()
            Constants.BUSINESS -> BusinessFragment()
            Constants.CULTURE -> CultureFragment()
            else -> throw IllegalArgumentException("Unknown Fragment")
        }
    }


    override fun getCount(): Int {
        return 9
    }


    override fun getPageTitle(position: Int): CharSequence? {
        val titleResId: Int
        titleResId = when (position) {
            Constants.HOME -> R.string.ic_title_home
            Constants.WORLD -> R.string.ic_title_world
            Constants.SCIENCE -> R.string.ic_title_science
            Constants.SPORT -> R.string.ic_title_sport
            Constants.ENVIRONMENT -> R.string.ic_title_environment
            Constants.SOCIETY -> R.string.ic_title_society
            Constants.FASHION -> R.string.ic_title_fashion
            Constants.BUSINESS -> R.string.ic_title_business
            else -> R.string.ic_title_culture
        }
        return mContext.getString(titleResId)
    }
}