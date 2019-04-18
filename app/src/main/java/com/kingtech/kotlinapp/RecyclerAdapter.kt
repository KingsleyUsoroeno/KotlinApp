package com.kingtech.kotlinapp

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerAdapter(private val homeFeed: HomeFeed) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
       return homeFeed.video.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videos = homeFeed.video.get(position)
        holder.itemView.tv_name.text = videos.name
        holder.itemView.tv_numberOfViews.text = videos.numberOfViews.toString()
        holder.itemView.tv_channel_name.text = videos.channel.name
        Picasso.get().load(videos.imageUrl).into(holder.itemView.user_image)
        holder.videos = videos
    }

    class ViewHolder(itemView: View,var videos: Videos? = null) : RecyclerView.ViewHolder(itemView) {
        // The ? mark means that these argument is optionally to be passed into the param
        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context,CourseDetailActivity::class.java)
                intent.putExtra(COURSE_NAME,videos?.name)
                intent.putExtra(COURSE_ID,videos?.id)
                itemView.context.startActivity(intent)
            }
        }
        companion object {
            const val COURSE_NAME = "course_name"
            const val COURSE_ID = "course_id"
        }
    }
}