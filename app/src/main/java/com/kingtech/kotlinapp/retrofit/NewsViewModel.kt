package com.kingtech.kotlinapp.retrofit

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "NewsViewModel"

class NewsViewModel : ViewModel() {

    private val _newsLiveData = MutableLiveData<List<Articles>>()

    private var service: NewsService = NewsConnector.createRetrofitInstance()!!
        .create(NewsService::class.java)

    // getter for our LiveData
    val newsData: LiveData<List<Articles>>
        get() = _newsLiveData


    init {
        loadNewsData()
    }

    private fun loadNewsData() {
        println("Attempting to Fetch Data from NewsApi")
        val call: Call<NewsApi> =
            service.getNewsData("bitcoin", "2019-04-25", "publishedAt", "b37668ef0d1e4ac283ad4c621cc396cf")
        Log.i(TAG, "url called ${call.request().url()}")

        call.enqueue(object : Callback<NewsApi> {
            override fun onResponse(call: Call<NewsApi>, response: Response<NewsApi>) {
                if (response.body() != null) {
                    _newsLiveData.value = response.body()!!.articles
                    Log.i(TAG, "articleList ${response.body()!!.articles}")
                }
            }

            override fun onFailure(call: Call<NewsApi>, t: Throwable) {
                Log.i(TAG, "Failed to fetch our Needed Data due to ${t.message}")
            }
        })
    }
}