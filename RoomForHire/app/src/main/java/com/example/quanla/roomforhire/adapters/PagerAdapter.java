package com.example.quanla.roomforhire.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.quanla.roomforhire.fragments.AddFragment;
import com.example.quanla.roomforhire.fragments.ApartmentFragment;
import com.example.quanla.roomforhire.fragments.HomeFragment;
import com.example.quanla.roomforhire.fragments.MapFragment;
import com.example.quanla.roomforhire.fragments.MarkFragment;
import com.example.quanla.roomforhire.fragments.RoomFragment;
import com.example.quanla.roomforhire.fragments.SearchFragment;
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
            case 1:
                fragment = new MarkFragment();
                break;
            case 0:
                fragment = new HomeFragment();
                break;
            case 2:
                fragment = new AddFragment();
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


}
