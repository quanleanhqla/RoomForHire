package com.example.quanla.roomforhire.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.adapters.viewholders.RoomHolder;
import com.example.quanla.roomforhire.dataFake.DataFake;
import com.example.quanla.roomforhire.dataFake.DataRoom;
import com.example.quanla.roomforhire.dataFake.models.Room;
import com.example.quanla.roomforhire.events.MoveToMap;
import com.example.quanla.roomforhire.events.RoomEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by QuanLA on 4/20/2017.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomHolder> {
    @Override
    public RoomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_room, parent, false);

        RoomHolder roomHolder = new RoomHolder(view);
        return roomHolder;
    }

    @Override
    public void onBindViewHolder(RoomHolder holder, int position) {
        final Room room = DataRoom.instance.getAllRoom().get(position);

        holder.bind(room);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new RoomEvent(room, MoveToMap.FROMDETAIL));
            }
        });

    }

    @Override
    public int getItemCount() {
        return DataRoom.instance.getAllRoom().size();
    }
}
