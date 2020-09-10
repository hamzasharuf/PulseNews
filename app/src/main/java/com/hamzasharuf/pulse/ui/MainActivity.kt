package com.hamzasharuf.pulse.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.databinding.ActivityMainBinding
import com.hamzasharuf.pulse.databinding.ActivityMainBindingImpl
import com.hamzasharuf.pulse.utils.extensions.activityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by activityBinding<ActivityMainBinding>(R.layout.activity_main)
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        setSupportActionBar(main_screen_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initNavigation()
    }

    private fun initNavigation() {
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
    }
}