package com.hamzasharuf.pulse.ui.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.data.models.NewsSection
import com.hamzasharuf.pulse.databinding.FragmentViewPagerBinding
import com.hamzasharuf.pulse.ui.NavigationSharedViewModel
import com.hamzasharuf.pulse.utils.adapters.lists.news.CategoryFragmentPagerAdapter

class ViewPagerFragment : Fragment() {

    private val viewModel: ViewPagerViewModel by viewModels()
    private val sharedViewModel: NavigationSharedViewModel by activityViewModels()
    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_pager, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewpager2.adapter = CategoryFragmentPagerAdapter(childFragmentManager , lifecycle)
        val tabLayout: TabLayout = requireActivity().findViewById(R.id.sliding_tabs)
        TabLayoutMediator(tabLayout, binding.viewpager2) { tab, position ->
            tab.text = NewsSection.getItem(position).sectionName
            binding.viewpager2.setCurrentItem(tab.position, true)
        }.attach()
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        sharedViewModel.viewPager = binding.viewpager2
    }

}