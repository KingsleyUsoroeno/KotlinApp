package com.kingtech.kotlinapp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v2/everything")
    fun getNewsData(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("sortBy") sortedBy: String,
        @Query("apiKey") apiKey: String
    ):Call<NewsApi>

}