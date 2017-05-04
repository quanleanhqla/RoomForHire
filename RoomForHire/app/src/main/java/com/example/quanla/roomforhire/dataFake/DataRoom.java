package com.example.quanla.roomforhire.dataFake;

import com.example.quanla.roomforhire.dataFake.models.Room;

import java.util.ArrayList;

/**
 * Created by QuanLA on 5/4/2017.
 */

public class DataRoom {
    private ArrayList<Room> allRoom;

    public static final DataRoom instance  = new DataRoom();

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
