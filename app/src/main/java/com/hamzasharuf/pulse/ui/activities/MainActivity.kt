package com.hamzasharuf.pulse.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.data.models.NewsSection
import com.hamzasharuf.pulse.databinding.ActivityMainBinding
import com.hamzasharuf.pulse.ui.NavigationSharedViewModel
import com.hamzasharuf.pulse.utils.adapters.lists.news.CategoryFragmentPagerAdapter
import com.hamzasharuf.pulse.utils.extensions.activityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_view_pager.*
import kotlinx.android.synthetic.main.toolbar_tab_layout.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val binding: ActivityMainBinding by activityBinding(R.layout.activity_main)
    private val sharedViewModel: NavigationSharedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupObservers()
        setupViewPager()
        setupDrawer()
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        setSupportActionBar(toolbar)
    }

    private fun setupObservers() {

    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        onNavigationItemSelected(nav_view.menu.getItem(0).setChecked(true))

    }

    private fun setupViewPager() {
        viewpager.adapter = CategoryFragmentPagerAdapter(supportFragmentManager , lifecycle)
        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            tab.text = NewsSection.getItem(position).sectionName
            viewpager.setCurrentItem(tab.position, true)
        }.attach()
        tablayout.tabGravity = TabLayout.GRAVITY_FILL
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            // TODO : Navigate to Settings Activity
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /** Navigate to the selected page from the navigation drawer */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val section = sharedViewModel.navigateToPagerSection(item.itemId)
        viewpager.currentItem = section.page
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}