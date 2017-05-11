package com.example.quanla.roomforhire.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.activities.CoreActivity;
import com.example.quanla.roomforhire.adapters.RoomAdapter;
import com.example.quanla.roomforhire.dataFake.DataRoom;
import com.example.quanla.roomforhire.dataFake.models.Room;
import com.example.quanla.roomforhire.events.ReplaceFragmentEvent;
import com.example.quanla.roomforhire.events.RoomEvent;
import com.example.quanla.roomforhire.events.TitleEvent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApartmentFragment extends Fragment {

    @BindView(R.id.rvroom)
    RecyclerView rv;
    private ProgressDialog progressDialog;

    private String[] allName = {"Bán căn hộ chung cư"};
    private static String TAG = "dcm";

    private RoomAdapter roomAdapter;
    final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();


    public ApartmentFragment() {
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
        View view = inflater.inflate(R.layout.fragment_apartment, container, false);
        ButterKnife.bind(this, view);
        roomAdapter = new RoomAdapter();
        rv.setAdapter(roomAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        progressDialog =  new ProgressDialog(this.getContext());

        loadData();
        return view;
    }

    @Subscribe(sticky = true)
    public void replaceFrag(RoomEvent roomEvent){
        EventBus.getDefault().post(new ReplaceFragmentEvent(new DetailRoom(), true));
    }

    @Subscribe(sticky = true)
    public void getTitle(TitleEvent titleEvent){
        if(getActivity() instanceof CoreActivity){
            ((CoreActivity) getActivity()).getSupportActionBar().setTitle(titleEvent.getTitle());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(TitleEvent.class);
    }

    public void loadData(){
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                DataRoom.instance.clear();
//                for(int i=0; i<allName.length; i++){
//                    Room room = dataSnapshot.child("apartment").child(allName[i]).getValue(Room.class);
//                    DataRoom.instance.add(room);
//                }
//                progressDialog.dismiss();
//                roomAdapter.notifyDataSetChanged();
//                Log.d(TAG, String.format("%s", DataRoom.instance.getAllRoom().toString()));
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        DataRoom.instance.clear();
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        databaseReference.child("apartment").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Room room = dataSnapshot.getValue(Room.class);
                DataRoom.instance.add(room);
                progressDialog.dismiss();
                roomAdapter.notifyDataSetChanged();
                Log.d(TAG, String.format("%s", DataRoom.instance.getAllRoom().toString()));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
