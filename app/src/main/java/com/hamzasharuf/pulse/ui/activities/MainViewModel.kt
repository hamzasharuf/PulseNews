package com.hamzasharuf.pulse.ui.activities

import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

}