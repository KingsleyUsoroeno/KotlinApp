package com.kingtech.kotlinapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsConnector {

    companion object {
        var retrofit: Retrofit? = null
        private val BASE_URL: String = "https://newsapi.org/"

        fun createRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }

}