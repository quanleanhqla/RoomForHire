package com.example.quanla.roomforhire.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.adapters.RoomAdapter;
import com.example.quanla.roomforhire.events.ReplaceFragmentEvent;
import com.example.quanla.roomforhire.events.RoomEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomFragment extends Fragment {


    @BindView(R.id.rvroom)
    RecyclerView rv;

    private RoomAdapter roomAdapter;

    public RoomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        ButterKnife.bind(this, view);

        roomAdapter = new RoomAdapter();
        rv.setAdapter(roomAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    @Subscribe(sticky = true)
    public void replaceFrag(RoomEvent roomEvent){
        EventBus.getDefault().post(new ReplaceFragmentEvent(new DetailRoom(), true));
    }

}
