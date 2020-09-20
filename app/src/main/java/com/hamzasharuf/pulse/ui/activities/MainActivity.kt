package com.hamzasharuf.pulse.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.databinding.ActivityMainBinding
import com.hamzasharuf.pulse.utils.extensions.activityBinding
import com.hamzasharuf.pulse.utils.states.ScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val binding: ActivityMainBinding by activityBinding(R.layout.activity_main)
    private val sharedViewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.appBarLayout.toolbar)
        setupBinding()
        setupNavController()
        setupObservers()
        setupDrawer()
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
    }

    private fun setupNavController() {
        navController = findNavController(R.id.fragment_container)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
    }

    private fun setupObservers() {
        sharedViewModel.screenState.observe(this, {
            when (it) {
                ScreenState.NewsScreenState -> {

                }
                ScreenState.DetailsScreenState -> {
                    navController.navigate(R.id.action_viewPagerFragment_to_newsDetailsFragment)
                }
                ScreenState.SettingsScreenState -> {
                    navController.navigate(R.id.action_viewPagerFragment_to_settingsFragment)
                }
            }
        })
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarLayout.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        onNavigationItemSelected(binding.navView.menu.getItem(0).setChecked(true))

    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
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
            navController.navigate(R.id.action_viewPagerFragment_to_settingsFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /** Navigate to the selected page from the navigation drawer */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val section = sharedViewModel.navigateToPagerSection(item.itemId)
        if (sharedViewModel.screenState.value == ScreenState.NewsScreenState)
            sharedViewModel.viewPager.currentItem = section.page
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /** Navigate back to the previous fragment using back arrow */
    override fun onSupportNavigateUp(): Boolean {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}