package com.kingtech.kotlinapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_layout.view.*

class NewsAdapter(private val articles: List<Articles>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_layout,parent,false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val articles = articles.get(position)
        holder.bindViews(articles)
    }

    class NewsViewHolder(var view:View) : RecyclerView.ViewHolder(view) {

        fun bindViews(articles: Articles){
            view.tv_news_author.text = articles.author
            view.tv_news_title.text = articles.newsTitle
            view.tv_news_description.text = articles.newsDescription
            if (articles.imageUrl.isEmpty()){
                Picasso.get().load("https://images.spot.im/v1/production/ysx7c1kkh5kscbdsprwx").into(view.news_image)
            }else{
                Picasso.get().load(articles.imageUrl).into(view.news_image)
            }

        }
    }

}
