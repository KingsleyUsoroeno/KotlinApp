package com.kingtech.kotlinapp

import com.google.gson.annotations.SerializedName

class NewsApi(
    @SerializedName("articles")
    var articles: List<Articles>
)

class Articles(
    @SerializedName("author") val author: String,
    @SerializedName("title") val newsTitle: String,
    @SerializedName("description") val newsDescription: String,
    @SerializedName("url") val newsUrl: String,
    @SerializedName("urlToImage") val imageUrl: String
)


