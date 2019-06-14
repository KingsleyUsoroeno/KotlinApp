package com.kingtech.kotlinapp.retrofit

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kingtech.kotlinapp.databinding.GridRecyclerLayoutBinding

class NewsAdapter(private val clickListener: NewsArticleClickListener) :
    ListAdapter<Articles, NewsAdapter.NewsViewHolder>(NewsArticleDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GridRecyclerLayoutBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val articles = getItem(position)
        holder.bindViews(articles, clickListener)
    }

    class NewsViewHolder(private val binding: GridRecyclerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindViews(articles: Articles, listener: NewsArticleClickListener) {
            binding.article = articles
            binding.onClickListerner = listener
            binding.executePendingBindings()
        }
    }
}

class NewsArticleDiffCallBack : DiffUtil.ItemCallback<Articles>() {

    override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
        return oldItem.author == newItem.author
    }

    override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
        return oldItem == newItem
    }

}

class NewsArticleClickListener(val clickListener: (article: Articles) -> Unit) {
    fun onClick(articles: Articles) = clickListener(articles)
}
