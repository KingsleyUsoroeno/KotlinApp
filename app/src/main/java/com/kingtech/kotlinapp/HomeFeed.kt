package com.kingtech.kotlinapp

import com.google.gson.annotations.SerializedName

class HomeFeed(@SerializedName("videos") val video: List<Videos>)

class Videos(val id: Int, val name: String, val imageUrl: String, val numberOfViews: Int, val channel: Channel)

class Channel(val name: String, val profileImageUrl: String)

class CourseDetail(val name: String, val duration: String, val number: Int, val imageUrl: String, val link: String)