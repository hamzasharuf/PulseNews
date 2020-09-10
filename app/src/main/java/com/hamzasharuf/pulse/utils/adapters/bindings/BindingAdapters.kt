package com.hamzasharuf.pulse.utils.adapters.bindings

import android.content.res.Configuration
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.utils.DateFormatter

/**
 * Load image from the network or cache with placeholder and error images
 */
@BindingAdapter("imageLoad")
fun loadImage(imageView: ImageView, url: String?) {
    url?.let {
        imageView.load(it) {
            crossfade(true)
            fallback(R.color.black)
            placeholder(R.color.colorBorder)
            when (imageView.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    transformations(RoundedCornersTransformation(0.0F))
                } // Night mode is not active, we're using the light theme
                Configuration.UI_MODE_NIGHT_YES -> {
                    transformations(GrayscaleTransformation())
                } // Night mode is active, we're using dark theme
            }
        }
    }
}

/**
 *  Get Relative Date (Yesterday, 2 hours ago, last week, ...etc)
 */
@BindingAdapter("relativeDate")
fun relativeDate(textView: TextView,inputDate: String?) {
    if (inputDate != null) {
        val relativeDate = DateFormatter.getRelativeDate(inputDate)
        textView.text = relativeDate
    }

}

/**
 *  Get Calendar Date (Thursday, 6 August 2020)
 */
@BindingAdapter("calendarDate")
fun date(textView: TextView,inputDate: String?) {
    if (inputDate != null) {
        val calendarDate = DateFormatter.getCalendarDate(inputDate)
        textView.text = calendarDate
    }

}
