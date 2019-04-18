package com.kingtech.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        newsRecyclerView.setHasFixedSize(true)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        loadNewsData()
    }

    private fun loadNewsData() {
        println("Attempting to Fetch Data from NewsApi")
        val service: NewsService = NewsConnector.createRetrofitInstance()!!.create(NewsService::class.java)
        val call: Call<NewsApi> =
            service.getNewsData("bitcoin", "2019-03-18", "publishedAt", "b37668ef0d1e4ac283ad4c621cc396cf")
        call.enqueue(object : Callback<NewsApi> {
            override fun onResponse(call: Call<NewsApi>, response: Response<NewsApi>) {
                if (response.isSuccessful){
                    val articles = response.body()!!.articles
                    runOnUiThread{
                        newsRecyclerView.adapter = NewsAdapter(articles)
                    }
                }
            }

            override fun onFailure(call: Call<NewsApi>, t: Throwable) {
                println("Failed to fetch our Needed Data due to ${t.message}")
            }
        })
    }
}
