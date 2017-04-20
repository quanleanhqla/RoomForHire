package com.example.quanla.roomforhire.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.dataFake.models.Room;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QuanLA on 4/20/2017.
 */

public class RoomHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.price)
    TextView price;

    public RoomHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Room room){
        iv.setImageResource(R.drawable.room);
        title.setText(room.getTitle());
        address.setText("110 Xuân Thủy, Cầu Giấy, Hà Nội");
        price.setText(room.getPrice());
    }
}
