package com.hamzasharuf.pulse.data.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val author: String?, // Jonathan Shieber
    val content: String?, // Casa, a Colorado-based provider of bitcoin security services, is launching a managed service allowing customers to buy and hold their own bitcoin, rather than using an external custodian like Coinbas… [+1571 chars]
    val description: String?, // Casa, a Colorado-based provider of bitcoin security services, is launching a managed service allowing customers to buy and hold their own bitcoin, rather than using an external custodian like Coinbase. “With self-custody using Casa it’s impossible to be hacke…
    val publishedAt: String?, // 2020-08-06T18:25:29Z
    val source_id: String?, // techcrunch
    val source_name: String?, // TechCrunch
    val title: String?, // Casa pivots to provide self-custody services to secure bitcoin
    val url: String?, // http://techcrunch.com/2020/08/06/casa-pivots-to-provide-self-custody-services-to-secure-bitcoin/
    val urlToImage: String? // https://techcrunch.com/wp-content/uploads/2019/06/GettyImages-1050523528.jpg?w=600
) : Parcelable {
    companion object{
        val diffUtilsCallback = object: DiffUtil.ItemCallback<Article>(){

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url.equals(newItem.url)
            }

        }
    }
}