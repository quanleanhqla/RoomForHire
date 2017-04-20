package com.example.quanla.roomforhire.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.quanla.roomforhire.fragments.ApartmentFragment;
import com.example.quanla.roomforhire.fragments.MapFragment;
import com.example.quanla.roomforhire.fragments.RoomFragment;
import com.example.quanla.roomforhire.fragments.VillaFragment;

/**
 * Created by QuanLA on 4/20/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new RoomFragment();
                break;
            case 1:
                fragment = new ApartmentFragment();
                break;
            case 2:
                fragment = new VillaFragment();
                break;
            case 3:
                fragment = new MapFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Phòng trọ";
                break;
            case 1:
                title = "Căn hộ";
                break;
            case 2:
                title = "Biệt thự";
                break;
            case 3:
                title = "Quanh đây";
                break;
        }
        return title;
    }
}
