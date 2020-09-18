package com.hamzasharuf.pulse.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.databinding.ActivityMainBinding
import com.hamzasharuf.pulse.utils.Constants
import com.hamzasharuf.pulse.utils.adapters.lists.news.CategoryFragmentPagerAdapter
import com.hamzasharuf.pulse.utils.extensions.activityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val binding: ActivityMainBinding by activityBinding(R.layout.activity_main)
    private val viewModel: MainViewModel by viewModels()
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        viewPager = binding.appBarLayout.contentMainLayout.viewpager
        setSupportActionBar(binding.appBarLayout.toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarLayout.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Give the TabLayout the ViewPager

        // Give the TabLayout the ViewPager
        val tabLayout = binding.appBarLayout.slidingTabs
        tabLayout.setupWithViewPager(viewPager)
        // Set gravity for tab bar
        // Set gravity for tab bar
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val navigationView = findViewById<NavigationView>(R.id.nav_view)!!
        navigationView.setNavigationItemSelectedListener(this)

        // Set the default fragment when starting the app

        // Set the default fragment when starting the app
        onNavigationItemSelected(navigationView.menu.getItem(0).setChecked(true))

        // Set category fragment pager adapter

        // Set category fragment pager adapter
        val pagerAdapter = CategoryFragmentPagerAdapter(
            this,
            supportFragmentManager
        )
        // Set the pager adapter onto the view pager
        // Set the pager adapter onto the view pager
        viewPager.adapter = pagerAdapter

    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.nav_home) {
            viewPager.setCurrentItem(Constants.HOME)
        } else if (id == R.id.nav_world) {
            viewPager.setCurrentItem(Constants.WORLD)
        } else if (id == R.id.nav_science) {
            viewPager.setCurrentItem(Constants.SCIENCE)
        } else if (id == R.id.nav_sport) {
            viewPager.setCurrentItem(Constants.SPORT)
        } else if (id == R.id.nav_environment) {
            viewPager.setCurrentItem(Constants.ENVIRONMENT)
        } else if (id == R.id.nav_society) {
            viewPager.setCurrentItem(Constants.SOCIETY)
        } else if (id == R.id.nav_fashion) {
            viewPager.setCurrentItem(Constants.FASHION)
        } else if (id == R.id.nav_business) {
            viewPager.setCurrentItem(Constants.BUSINESS)
        } else if (id == R.id.nav_culture) {
            viewPager.setCurrentItem(Constants.CULTURE)
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    // Initialize the contents of the Activity's options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the Options Menu we specified in XML
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    // This method is called whenever an item in the options menu is selected.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsIntent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}