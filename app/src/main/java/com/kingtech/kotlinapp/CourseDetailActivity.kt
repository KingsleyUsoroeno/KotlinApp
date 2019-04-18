package com.kingtech.kotlinapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_course_detail.*
import kotlinx.android.synthetic.main.course_detail.view.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class CourseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        val navBarTitle = intent.getStringExtra(RecyclerAdapter.ViewHolder.COURSE_NAME)
        val courseId = intent.getIntExtra(RecyclerAdapter.ViewHolder.COURSE_ID, 0)
        supportActionBar?.title = navBarTitle

        course_recyclerView.layoutManager = LinearLayoutManager(this)
        course_recyclerView.setHasFixedSize(true)

        fetchCourseDetail(courseId)
    }

    private fun fetchCourseDetail(id: Int) {
        println("Attempting to fetch Course Detail Json")
        val courseDetailUrl = "https://api.letsbuildthatapp.com/youtube/course_detail?id=$id"
        val request = Request.Builder().url(courseDetailUrl).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val res = response.body()!!.string()
                    extractJson(res)
                    // A more similar way around these would be to use the
                    // Gson library and convert the array response into a subtle class like
                    // var courseArray = Gson().fromJson(res, Array<CourseDetail>::class.java)
                    // var courseArray = Gson().fromJson(res, arrayListOf<CourseDetail>()::class.java)

//
                } else {
                    println("response was not Successful")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to fetch Course Detail ${e.message}")
            }

        })
    }

    private fun extractJson(jsonResponse: String) {
        val courseArray = JSONArray(jsonResponse)
        val array = arrayListOf<CourseDetail>()
        for (i in 0 until courseArray.length()) {
            val jsonObject = courseArray.getJSONObject(i)
            val name = jsonObject.getString("name")
            val duration = jsonObject.getString("duration")
            val number = jsonObject.getInt("number")
            val imageUrl = jsonObject.getString("imageUrl")
            val link = jsonObject.getString("link")
            val courseDetail = CourseDetail(name, duration, number, imageUrl, link)
            array.add(courseDetail)
        }

        runOnUiThread {
            course_recyclerView.adapter = CourseDetailAdapter(array)
        }
    }
}

class CourseDetailAdapter(private val courseArray: List<CourseDetail>) :
    RecyclerView.Adapter<CourseDetailAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.course_detail, parent, false)
        return CourseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return courseArray.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val courseDetail = courseArray.get(position)
        holder.setData(courseDetail, position)

    }

    class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var courseDetail: CourseDetail? = null
        var currentPosition: Int = 0

        // How we can Declare static fields in our classes
        companion object {
            const val COURSE_LINK = "COURSE_LINK"
            const val COURSE_NAME = "COURSE_NAME"
        }

        init {
            view.parent_layout.setOnClickListener {
                Toast.makeText(view.context, "You Clicked on ${courseDetail?.name}", Toast.LENGTH_LONG).show()
                println("Course Link is ${courseDetail?.link}")
                val intent = Intent(view.context,CourseWebPageActivity::class.java)
                intent.putExtra(COURSE_LINK,courseDetail?.link)
                intent.putExtra(COURSE_NAME,courseDetail?.name)
                view.context.startActivity(intent)
            }
        }

        fun setData(courseDetail: CourseDetail?, position: Int) {
            itemView.tv_course_title.text = courseDetail?.name
            itemView.tv_course_episode.text = courseDetail?.duration
            Picasso.get().load(courseDetail?.imageUrl).into(itemView.img_course)

            this.courseDetail = courseDetail
            this.currentPosition = position
        }
    }
}
