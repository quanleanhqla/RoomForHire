package com.example.quanla.roomforhire.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.activities.CoreActivity;
import com.example.quanla.roomforhire.activities.LoginActivity;
import com.example.quanla.roomforhire.adapters.PagerAdapter;
import com.example.quanla.roomforhire.dataFake.DataFake;
import com.example.quanla.roomforhire.dataFake.models.Room;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    @BindView(R.id.vp_horizontal_ntb)
    ViewPager viewPager;
    @BindView(R.id.ntb_horizontal)
    NavigationTabBar navigationTabBar;


    final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseAuth firebaseAuth;




    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        if(getActivity() instanceof CoreActivity){
            ((CoreActivity) getActivity()).getSupportActionBar().setTitle("Trang chủ");
        }

        firebaseAuth = FirebaseAuth.getInstance();


        initUI();


        return view;
    }

    private void initUI() {

        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);



        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_home_white_24px),
                        Color.parseColor("#ffffff"))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_home_white_24px))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_stars_white_24px),
                        Color.parseColor("#ffffff"))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_stars_white_24px))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_add_box_white_24px),
                        Color.parseColor("#ffffff"))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_add_box_white_24px))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_nearby),
                        Color.parseColor("#ffffff"))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_nearby))
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                if(position==0){
                    if(getActivity() instanceof CoreActivity){
                        ((CoreActivity) getActivity()).getSupportActionBar().setTitle("Trang chủ");
                    }
                }
                else if(position==1){
                    if(getActivity() instanceof CoreActivity){
                        ((CoreActivity) getActivity()).getSupportActionBar().setTitle("Yêu thích");
                    }
                }
                else if(position==2){
                    if(getActivity() instanceof CoreActivity){
                        ((CoreActivity) getActivity()).getSupportActionBar().setTitle("Đăng tin");
                    }
                }
                else {
                    if(getActivity() instanceof CoreActivity){
                        ((CoreActivity) getActivity()).getSupportActionBar().setTitle("Gần đây");
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }



}
