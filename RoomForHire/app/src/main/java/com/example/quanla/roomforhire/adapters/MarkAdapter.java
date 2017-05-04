//package com.example.quanla.roomforhire.adapters;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.quanla.roomforhire.R;
//import com.example.quanla.roomforhire.adapters.viewholders.RoomHolder;
//import com.example.quanla.roomforhire.dataFake.DataMark;
//import com.example.quanla.roomforhire.dataFake.DataRoom;
//import com.example.quanla.roomforhire.dataFake.models.Room;
//import com.example.quanla.roomforhire.events.RoomEvent;
//
//import org.greenrobot.eventbus.EventBus;
//
///**
// * Created by QuanLA on 5/5/2017.
// */
//
//public class MarkAdapter extends RecyclerView.Adapter<RoomHolder> {
//    @Override
//    public RoomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.item_mark, parent, false);
//
//        RoomHolder roomHolder = new RoomHolder(view);
//        return roomHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(RoomHolder holder, int position) {
//        final Room room = DataMark.instance.getAllRoom().get(position);
//
//        holder.bind(room);
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().postSticky(new RoomEvent(room));
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return DataMark.instance.getAllRoom().size();
//    }
//}
