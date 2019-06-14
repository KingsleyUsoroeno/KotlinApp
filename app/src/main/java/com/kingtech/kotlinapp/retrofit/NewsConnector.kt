package com.kingtech.kotlinapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsConnector {

    // Companion object is How to create Static variables in Kotlin
    companion object {
        var retrofit: Retrofit? = null
        private const val BASE_URL: String = "https://newsapi.org/"

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