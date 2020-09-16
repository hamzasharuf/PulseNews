package com.hamzasharuf.pulse.data.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsSource(
    val category: String?, // general
    val country: String?, // us
    val description: String?, // Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.
    val id: String?, // abc-news
    val language: String?, // en
    val name: String?, // ABC News
    val url: String?, // https://abcnews.go.com

) : Parcelable{

    @IgnoredOnParcel
    var isChecked: Boolean = false // false

//    @IgnoredOnParcel
//    var position: Int = 0 // false

    companion object{
        val diffUtilsCallback = object : DiffUtil.ItemCallback<NewsSource>(){
            override fun areItemsTheSame(oldItem: NewsSource, newItem: NewsSource): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: NewsSource, newItem: NewsSource): Boolean =
                oldItem.id.equals(newItem.id)
        }
    }

}