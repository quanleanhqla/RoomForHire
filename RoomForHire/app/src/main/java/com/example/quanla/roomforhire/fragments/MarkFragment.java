package com.example.quanla.roomforhire.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanla.roomforhire.R;



import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarkFragment extends Fragment {

    @BindView(R.id.rv)
    RecyclerView rv;

    //private MarkAdapter markAdapter;
    public MarkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mark, container, false);
        ButterKnife.bind(this, view);
//        markAdapter = new MarkAdapter();
//        rv.setAdapter(markAdapter);
//        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //markAdapter.notifyDataSetChanged();
    }
}
