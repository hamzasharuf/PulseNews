package com.hamzasharuf.pulse.utils.adapters.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamzasharuf.pulse.data.models.Article
import com.hamzasharuf.pulse.databinding.ListItemArticleBinding
import java.lang.ClassCastException
import javax.inject.Inject

/*
    class NewsAdapter(private val onItemClick:(Article) -> Unit)
    : ListAdapter<Article, RecyclerView.ViewHolder>(Article.diffUtilsCallback)
*/

class NewsAdapter (private val clickListener: NewsClickListener)
    : ListAdapter<Article, RecyclerView.ViewHolder>(Article.diffUtilsCallback) {

    companion object{
        private const val ARTICLE_VIEW_TYPE = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ARTICLE_VIEW_TYPE -> NewsViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is NewsViewHolder ->{
                val item = getItem(position) as Article
                holder.bind(item, clickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is Article -> ARTICLE_VIEW_TYPE
            else -> throw ClassCastException("Unknown item: ${getItem(position)}")
        }
    }

    class NewsViewHolder private constructor(val binding: ListItemArticleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article, clickListener: NewsClickListener){
            binding.clickListener = clickListener
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemArticleBinding.inflate(layoutInflater, parent, false)
                return NewsViewHolder(binding)
            }
        }
    }
}