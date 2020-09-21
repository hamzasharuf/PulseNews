package com.hamzasharuf.pulse.ui.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.databinding.ActivityMainBinding
import com.hamzasharuf.pulse.utils.common.CommonFunctions.getActionBarSize
import com.hamzasharuf.pulse.utils.extensions.activityBinding
import com.hamzasharuf.pulse.utils.extensions.setGone
import com.hamzasharuf.pulse.utils.states.ScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val binding: ActivityMainBinding by activityBinding(R.layout.activity_main)
    private val sharedViewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController
    private var tabLayoutHeight: Int = 0
    private var actionBarSize = 0
    private var isHidden: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.appBarLayout.toolbar)
        setupBinding()
        setupNavController()
        setupObservers()
        setupDrawer()
    }

    override fun onResume() {
        super.onResume()
        val preDrawListener = object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                binding.appBarLayout.slidingTabs.viewTreeObserver.removeOnPreDrawListener(this)
                // code which requires view size parameters
                tabLayoutHeight = binding.appBarLayout.slidingTabs.height
                actionBarSize = getActionBarSize(this@MainActivity)
                Timber.d("hhhhhhhh --> Height calculated = $tabLayoutHeight")
                Timber.d("hhhhhhhh --> Actionbar size = $actionBarSize")
                return true
            }
        }
        binding.appBarLayout.slidingTabs.viewTreeObserver.addOnPreDrawListener(preDrawListener)
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
//                    binding.appBarLayout.slidingTabs.setVisible()
                    if (!isHidden)
                        return@observe
                    val anim = ValueAnimator.ofInt(
                        actionBarSize,
                        actionBarSize + tabLayoutHeight,
                        )
                    anim.addUpdateListener { valueAnimator ->
                        val x = valueAnimator.animatedValue as Int
                        val layoutParams: ViewGroup.LayoutParams =
                            binding.appBarLayout.defaultAppbar.layoutParams
                        layoutParams.height = x
                        binding.appBarLayout.defaultAppbar.layoutParams = layoutParams
                    }
                    anim.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            isHidden = false
                        }
                    })
                    anim.duration = 350
                    anim.start()
                }
                ScreenState.DetailsScreenState -> {
                    navController.navigate(R.id.action_viewPagerFragment_to_newsDetailsFragment)


                    val anim = ValueAnimator.ofInt(
                        binding.appBarLayout.defaultAppbar.getMeasuredHeight(), getActionBarSize(
                            this
                        )
                    )
                    anim.addUpdateListener { valueAnimator ->
                        val x = valueAnimator.animatedValue as Int
                        val layoutParams: ViewGroup.LayoutParams =
                            binding.appBarLayout.defaultAppbar.layoutParams
                        layoutParams.height = x
                        binding.appBarLayout.defaultAppbar.layoutParams = layoutParams
                    }
                    anim.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            isHidden = true
                        }
                    })
                    anim.duration = 350
                    anim.start()

                }
                ScreenState.SettingsScreenState -> {
                    navController.navigate(R.id.action_viewPagerFragment_to_settingsFragment)
                    binding.appBarLayout.slidingTabs.setGone()
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