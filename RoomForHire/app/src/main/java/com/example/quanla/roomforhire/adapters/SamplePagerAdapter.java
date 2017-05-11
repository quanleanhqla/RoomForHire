package com.example.quanla.roomforhire.adapters;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.*;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanla.roomforhire.dataFake.DataPhoto;

import java.util.Random;

/**
 * Created by QuanLA on 5/9/2017.
 */

public class SamplePagerAdapter extends android.support.v4.view.PagerAdapter {

    private final Random random = new Random();
    private int mSize;

    public SamplePagerAdapter() {
        mSize = DataPhoto.instance.getAllPhoto().size();
    }

    public SamplePagerAdapter(int count) {
        mSize = count;
    }

    @Override public int getCount() {
        return mSize;
    }

    @Override public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    @Override public Object instantiateItem(ViewGroup view, int position) {
        ImageView imageView = new ImageView(view.getContext());
        imageView.setImageResource(DataPhoto.instance.getPhoto().get(position));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        view.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        return imageView;
    }

    public void addItem() {
        mSize++;
        notifyDataSetChanged();
    }

    public void removeItem() {
        mSize--;
        mSize = mSize < 0 ? 0 : mSize;

        notifyDataSetChanged();
    }
}
