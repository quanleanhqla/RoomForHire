package com.example.quanla.roomforhire.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.adapters.viewholders.PhotoHolder;
import com.example.quanla.roomforhire.dataFake.DataPhoto;
import com.example.quanla.roomforhire.dataFake.models.Photo;

/**
 * Created by QuanLA on 4/20/2017.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {
    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_photo, parent, false);

        PhotoHolder photoHolder = new PhotoHolder(view);
        return photoHolder;
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        Photo photo = DataPhoto.instance.getAllPhoto().get(position);

        holder.bind(photo);
    }

    @Override
    public int getItemCount() {
        return DataPhoto.instance.getAllPhoto().size();
    }
}
