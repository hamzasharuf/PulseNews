package com.hamzasharuf.pulse.ui

import androidx.hilt.Assisted
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.data.models.NewsSection
import com.hamzasharuf.pulse.utils.states.ScreenState
import timber.log.Timber
import java.lang.IllegalArgumentException
import javax.inject.Inject

class NavigationSharedViewModel
@Inject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState>
    get() = _screenState

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

    fun setScreenState(screen: ScreenState){
        if (screen == screenState.value)
            return
        _screenState.postValue(screen)
    }

    fun navigateToPagerSection(id: Int): NewsSection =
        when (id) {
            R.id.nav_home -> NewsSection.HOME
            R.id.nav_world -> NewsSection.WORLD
            R.id.nav_science -> NewsSection.SCIENCE
            R.id.nav_sport -> NewsSection.SPORT
            R.id.nav_environment -> NewsSection.ENVIRONMENT
            R.id.nav_society -> NewsSection.SOCIETY
            R.id.nav_fashion -> NewsSection.FASHION
            R.id.nav_business -> NewsSection.BUSINESS
            R.id.nav_culture -> NewsSection.CULTURE
            else -> throw IllegalArgumentException("Can't find tab with id: $id")
        }

    lateinit var viewPager: ViewPager2

}