package com.kingtech.kotlinapp.retrofit

import com.google.gson.annotations.SerializedName

class NewsApi(
    @SerializedName("articles")
    var articles: List<Articles>
)

data class Articles(
    @SerializedName("author") val author: String,
    @SerializedName("title") val newsTitle: String,
    @SerializedName("description") val newsDescription: String,
    @SerializedName("url") val newsUrl: String,
    @SerializedName("urlToImage") val imageUrl: String

)


