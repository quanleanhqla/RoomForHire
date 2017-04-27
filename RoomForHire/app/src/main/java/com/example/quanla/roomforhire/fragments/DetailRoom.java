package com.example.quanla.roomforhire.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.activities.MainActivity;
import com.example.quanla.roomforhire.adapters.PhotoAdapter;
import com.example.quanla.roomforhire.events.ReplaceFragmentEvent;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        //EventBus.getDefault().removeAllStickyEvents();
    }

    @Subscribe(sticky = true)
    public void getRoom(RoomEvent roomEvent){
        title.setText(roomEvent.getRoom().getTitle());
        address.setText("119 Xuân Thủy, Cầu Giấy, Hà Nội");
        price.setText(roomEvent.getRoom().getPrice());
        phone.setText(roomEvent.getRoom().getPhone());
        host.setText(roomEvent.getRoom().getHost());
        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(roomEvent.getRoom().getTitle());
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_location, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mn_location){
            EventBus.getDefault().post(new ReplaceFragmentEvent(new MapFragment(), true));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(RoomEvent.class);
    }
}
