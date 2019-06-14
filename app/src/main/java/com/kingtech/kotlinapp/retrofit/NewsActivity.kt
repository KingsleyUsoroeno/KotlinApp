package com.kingtech.kotlinapp.retrofit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.kingtech.kotlinapp.R
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel
    lateinit var adapter: NewsAdapter
    private val tag: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        adapter = NewsAdapter(NewsArticleClickListener { articles ->
            Log.i(tag, "article clicked was ${articles.author}")
        })
        newsViewModel.newsData.observe(this, Observer { articles ->
            if (articles != null) {
                initRecyclerView(articles)
            }
        })
    }

    private fun initRecyclerView(articleList: List<Articles>) {
        newsRecyclerView.setHasFixedSize(true)
        newsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter.submitList(articleList)
        newsRecyclerView.adapter = adapter
    }
}
