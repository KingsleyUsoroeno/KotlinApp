package com.kingtech.kotlinapp.retrofit

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

@BindingAdapter("authorName")
fun TextView.setAuthorNameFormatted(articles: Articles) {
    articles.let { newsArticle ->
        text = newsArticle.author
    }
}

@BindingAdapter("newsTitle")
fun TextView.newsTitleFormatted(article: Articles) {
    article.let { newsArticle ->
        text = newsArticle.newsTitle
    }
}

@BindingAdapter("description")
fun TextView.descriptionFormatted(article: Articles) {
    article.let { newsArticle ->
        text = newsArticle.newsDescription
    }
}

@BindingAdapter("imageFromUrl")
fun loadImageFromUrl(view: ImageView, article: Articles) {
    article.let { newsArticle ->
        Picasso.get().load(newsArticle.imageUrl).into(view)
    }
}