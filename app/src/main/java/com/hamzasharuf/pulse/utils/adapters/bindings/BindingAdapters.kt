package com.hamzasharuf.pulse.utils.adapters.bindings

import android.content.res.Configuration
import android.text.format.DateUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.utils.DateFormatter
import java.text.SimpleDateFormat
import java.util.*

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
 *  Date from and date to
 */
@BindingAdapter("relativeDate")
fun relativeDate(textView: TextView,inputDate: String?) {
    if (inputDate != null) {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val date = parser.parse(inputDate)!!


        // Get relative date (e.g. Now, 2 minutes ago, ...etc)
        val relativeDate = DateFormatter.getRelativeDate(date)
        textView.text = relativeDate
    }

}
