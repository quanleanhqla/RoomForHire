package com.example.quanla.roomforhire.events;

import android.support.v4.app.Fragment;

/**
 * Created by QuanLA on 4/21/2017.
 */

public class ReplaceFragmentEvent {
    private Fragment fragment;
    private boolean addToBackStack;

    public ReplaceFragmentEvent(Fragment fragment, boolean addToBackStack) {
        this.fragment = fragment;
        this.addToBackStack = addToBackStack;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public boolean isAddToBackStack() {
        return addToBackStack;
    }

    public void setAddToBackStack(boolean addToBackStack) {
        this.addToBackStack = addToBackStack;
    }

    @Override
    public String toString() {
        return "ReplaceFragmentEvent{" +
                "fragment=" + fragment +
                ", addToBackStack=" + addToBackStack +
                '}';
    }
}
