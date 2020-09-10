package com.hamzasharuf.pulse.ui

import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainActivityViewModel
@Inject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

}