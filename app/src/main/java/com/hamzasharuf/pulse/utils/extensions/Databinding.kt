package com.hamzasharuf.pulse.utils.extensions

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.hamzasharuf.pulse.utils.delegates.ActivityBindingProperty

internal fun <T : ViewDataBinding> activityBinding(@LayoutRes resId: Int) =
    ActivityBindingProperty<T>(resId)