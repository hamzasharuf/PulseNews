package com.hamzasharuf.pulse.utils.extensions

import android.app.Activity
import androidx.fragment.app.Fragment
import timber.log.Timber

private const val TAG = "timberTag  --> "

fun Activity.timber(message: String){
    Timber.d(TAG.plus(message))
}

fun Fragment.timber(message: String?){
    Timber.d(TAG.plus(message))
}

fun Any.timber(message: String?){
    Timber.d(TAG.plus(message))
}