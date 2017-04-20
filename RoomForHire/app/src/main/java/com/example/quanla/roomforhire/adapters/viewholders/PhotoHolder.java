package com.example.quanla.roomforhire.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.dataFake.models.Photo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QuanLA on 4/20/2017.
 */

public class PhotoHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_photo)
    ImageView imageView;
    public PhotoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Photo photo){
        imageView.setImageResource(photo.getLink());
    }
}
