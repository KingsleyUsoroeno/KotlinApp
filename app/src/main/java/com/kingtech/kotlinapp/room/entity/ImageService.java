package com.kingtech.kotlinapp.room.entity;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImageService {

    @Multipart
    @POST("api/v1/story/create")
    Call<ResponseBody> uploadImage(
            @Part("title") RequestBody title,
            @Part("story") RequestBody story,
            @Part MultipartBody.Part image

    );
}
