package com.example.quanla.roomforhire.dataFake;

import com.example.quanla.roomforhire.dataFake.models.Room;

import java.util.ArrayList;

/**
 * Created by QuanLA on 5/18/2017.
 */

public class DataSearch {
    private ArrayList<String> allRoom;

    public static final DataSearch instance  = new DataSearch();

    public void add(String title){
        allRoom.add(title);
    }

    public ArrayList<String> getAll(){
        if(allRoom==null){
            allRoom = new ArrayList<>();
        }
        return allRoom;
    }

    public void clear(){
        allRoom = new ArrayList<>();
    }
}
