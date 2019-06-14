package com.kingtech.kotlinapp.room;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.kingtech.kotlinapp.R;
import com.kingtech.kotlinapp.room.entity.ImageConnector;
import com.kingtech.kotlinapp.room.entity.ImageService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;

public class ImageUploadActivity extends AppCompatActivity {

    private static final String TAG = "ImageUploadActivity";
    private ImageService imageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        Button btnOpenFile = findViewById(R.id.btnOpenFile);
        imageService = ImageConnector.ImageConnectorInstance().create(ImageService.class);

        btnOpenFile.setOnClickListener(v -> initGalleryIntent());
    }

    private void initGalleryIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String imageFilePath = getRealPathFromGalleryPath(data.getData());
            uploadImage(imageFilePath);
        } else {
            return;
        }
    }

    private void uploadImage(String filePath) {
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image*/"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        RequestBody title = RequestBody.create(MultipartBody.FORM, "Hello from Retrofit");
        RequestBody story = RequestBody.create(MultipartBody.FORM, "Hows your Day so Far");

        Call<ResponseBody> responseBodyCall = imageService.uploadImage(
                title, story, fileToUpload
        );
        Log.d(TAG, "uploadImage: " + responseBodyCall.request().url());
        Toast.makeText(ImageUploadActivity.this, responseBodyCall.request().url().toString(), Toast.LENGTH_SHORT).show();

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        Log.d(TAG, "onResponse: " + response.body().string());
                        Toast.makeText(ImageUploadActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(ImageUploadActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getRealPathFromGalleryPath(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }
}
