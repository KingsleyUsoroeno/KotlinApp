package com.kingtech.kotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_you_tube.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class YouTubeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_tube)

        main_recyclerView.layoutManager = LinearLayoutManager(this)
//        main_recyclerView.addItemDecoration(RecyclerView.ItemDecoration,0)
        fetchJson()
    }

    private fun fetchJson() {
        println("Attempting to fetch json data")
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    val res = response.body()!!.string()
                    val homeFeed = gson.fromJson(res, HomeFeed::class.java)
                    runOnUiThread {
                        main_recyclerView.adapter = RecyclerAdapter(homeFeed)
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get response ${e.message}")
            }

        })
    }
}
