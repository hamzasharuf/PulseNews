package com.hamzasharuf.pulse.utils.adapters.bindings

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hamzasharuf.pulse.R
import com.hamzasharuf.pulse.utils.common.DateFormatter

/**
 * Load image from the network or cache with placeholder and error images
 */
@BindingAdapter("imageLoad")
fun loadImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .centerCrop()
        .placeholder(R.color.gray)
        .into(imageView)
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

@BindingAdapter("htmlText")
fun htmlText(textView: TextView, text: String?){
    if (text != null){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT).toString()
        } else {
            textView.text = Html.fromHtml(text).toString()
        }
    }
}
