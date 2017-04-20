package com.example.quanla.roomforhire.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.adapters.PhotoAdapter;
import com.example.quanla.roomforhire.events.RoomEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailRoom extends Fragment {

    @BindView(R.id.rv_anh)
    RecyclerView rv;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.tv_address)
    TextView address;
    @BindView(R.id.tv_price)
    TextView price;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.tv_host)
    TextView host;
    private PhotoAdapter photoAdapter;

    public DetailRoom() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_room, container, false);
        ButterKnife.bind(this, view);

        photoAdapter = new PhotoAdapter();
        rv.setAdapter(photoAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }

    @Subscribe(sticky = true)
    public void getRoom(RoomEvent roomEvent){
        title.setText(roomEvent.getRoom().getTitle());
        address.setText("119 Xuân Thủy, Cầu Giấy, Hà Nội");
        price.setText(roomEvent.getRoom().getPrice());
        phone.setText(roomEvent.getRoom().getPhone());
        host.setText(roomEvent.getRoom().getHost());

    }
}
