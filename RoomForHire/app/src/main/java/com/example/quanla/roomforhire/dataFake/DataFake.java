package com.example.quanla.roomforhire.dataFake;

import com.example.quanla.roomforhire.dataFake.models.Room;

import java.util.ArrayList;

/**
 * Created by QuanLA on 4/13/2017.
 */

public class DataFake {
    private ArrayList<Room> allRoom;

    public static final DataFake instance  = new DataFake();

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
