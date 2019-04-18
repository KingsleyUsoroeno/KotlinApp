package com.kingtech.kotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_course_web_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseWebPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_web_page)

        if (!intent.hasExtra(CourseDetailAdapter.CourseViewHolder.COURSE_LINK)) {
            return
        }
        supportActionBar?.title = intent.getStringExtra(CourseDetailAdapter.CourseViewHolder.COURSE_NAME)
        println(intent.getStringExtra(CourseDetailAdapter.CourseViewHolder.COURSE_LINK))
        web_view.settings.javaScriptEnabled = true
        web_view.settings.loadsImagesAutomatically = true
        web_view.loadUrl(intent.getStringExtra(CourseDetailAdapter.CourseViewHolder.COURSE_LINK))
    }

}
