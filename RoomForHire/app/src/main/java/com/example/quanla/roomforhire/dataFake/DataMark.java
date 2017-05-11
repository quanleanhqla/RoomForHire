package com.example.quanla.roomforhire.dataFake;

import android.content.Context;

import com.example.quanla.roomforhire.dataFake.models.Room;

import java.util.ArrayList;




/**
 * Created by QuanLA on 5/5/2017.
 */

public class DataMark {

    private ArrayList<Room> allRoom;

    public static final DataMark instance  = new DataMark();

    public void add(Room room){
        allRoom.add(room);
    }

    public ArrayList<Room> getAllRoom(){
        if(allRoom==null){
            allRoom = new ArrayList<>();
        }
        return allRoom;
    }

    public void clear(){
        allRoom = new ArrayList<>();
    }



}
