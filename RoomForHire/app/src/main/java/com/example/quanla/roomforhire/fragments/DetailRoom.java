package com.example.quanla.roomforhire.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.activities.CoreActivity;
import com.example.quanla.roomforhire.adapters.PhotoAdapter;

import com.example.quanla.roomforhire.dataFake.models.Room;
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
    @BindView(R.id.tv_detail)
    TextView tv_detail;
    @BindView(R.id.loai)
    TextView loai;
    @BindView(R.id.vung)
    TextView vung;
    @BindView(R.id.danhmuc)
    TextView danhmuc;
    @BindView(R.id.tinhtrang)
    TextView tinhtrang;
    @BindView(R.id.dientich)
    TextView dientich;
    @BindView(R.id.diachi)
    TextView diachi;
    @BindView(R.id.ll_call)
    LinearLayout ll_call;
    private Room room;
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
        ll_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
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
        room = roomEvent.getRoom();
        title.setText(roomEvent.getRoom().getTitle());
        address.setText(roomEvent.getRoom().getAddress());
        price.setText(roomEvent.getRoom().getPrice());
        phone.setText(roomEvent.getRoom().getPhone());
        host.setText(roomEvent.getRoom().getHost());
        loai.setText(roomEvent.getRoom().getType());
        vung.setText(roomEvent.getRoom().getVung());
        tinhtrang.setText(roomEvent.getRoom().getState());
        danhmuc.setText(roomEvent.getRoom().getDanhmuc());
        dientich.setText(roomEvent.getRoom().getDientich());
        diachi.setText(roomEvent.getRoom().getAddress());
        tv_detail.setText(roomEvent.getRoom().getDetail());
        if(getActivity() instanceof CoreActivity){
            ((CoreActivity) getActivity()).getSupportActionBar().setTitle(roomEvent.getRoom().getTitle());
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
//        if(item.getItemId()==R.id.mn_star){
//            DataMark.instance.addOrUpdate(room);
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(RoomEvent.class);
    }

    private void call() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:900"));
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    10);
            return;
        } else {
            try {
                startActivity(callIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this.getContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
