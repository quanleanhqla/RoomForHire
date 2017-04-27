package com.example.quanla.roomforhire.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.quanla.roomforhire.R;
import com.viven.imagezoom.ImageZoomHelper;

public class PhotoActivity extends AppCompatActivity {

    ImageZoomHelper imageZoomHelper;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imageView = (ImageView) findViewById(R.id.iv_image);
        imageZoomHelper = new ImageZoomHelper(this);
        ImageZoomHelper.setViewZoomable(findViewById(R.id.iv_image));
        ImageZoomHelper.setZoom(imageView, true);
        getSupportActionBar().hide();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return imageZoomHelper.onDispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }
}
