package com.example.quanla.roomforhire.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;


import com.example.quanla.roomforhire.R;
import com.github.chrisbanes.photoview.PhotoView;


public class PhotoActivity extends AppCompatActivity {


    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setScaleType(ImageView.ScaleType.CENTER);

        getSupportActionBar().hide();
    }


}
