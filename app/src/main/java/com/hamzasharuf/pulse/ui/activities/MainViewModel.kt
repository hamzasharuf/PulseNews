package com.hamzasharuf.pulse.ui.activities

import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.utils.NewsSection
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun setNavigationPage(id: Int): Int{
        return when(id){
            R.id.nav_home -> NewsSection.HOME.page
            R.id.nav_world -> NewsSection.WORLD.page
            R.id.nav_science -> NewsSection.SCIENCE.page
            R.id.nav_sport -> NewsSection.SPORT.page
            R.id.nav_environment -> NewsSection.ENVIRONMENT.page
            R.id.nav_society -> NewsSection.SOCIETY.page
            R.id.nav_fashion -> NewsSection.FASHION.page
            R.id.nav_business -> NewsSection.BUSINESS.page
            R.id.nav_culture -> NewsSection.CULTURE.page
            else -> throw IllegalArgumentException("Unknown id: $id")
        }
    }
}