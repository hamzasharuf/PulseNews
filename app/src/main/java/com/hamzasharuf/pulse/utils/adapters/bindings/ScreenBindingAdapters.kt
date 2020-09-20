package com.hamzasharuf.pulse.utils.adapters.bindings

import android.view.View
import androidx.databinding.BindingAdapter
import com.hamzasharuf.pulse.utils.extensions.setGone
import com.hamzasharuf.pulse.utils.extensions.setVisible
import com.hamzasharuf.pulse.utils.states.ScreenState


@BindingAdapter("defaultState")
fun View.setDefaultAppbarVisibility(state: ScreenState?) {
    if (state != null)
        if (state != ScreenState.DetailsScreenState)
            setVisible()
        else
            setGone()

}

@BindingAdapter("collapsingtState")
fun View.setCollapsingAppbarVisibility(state: ScreenState?) {
    if (state != null)
        if (state == ScreenState.DetailsScreenState)
            setVisible()
        else
            setGone()

}