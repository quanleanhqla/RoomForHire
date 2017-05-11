package com.example.quanla.roomforhire.dataFake;

import com.example.quanla.roomforhire.dataFake.models.Room;
import com.example.quanla.roomforhire.dataFake.models.UserProf;

import java.util.ArrayList;

/**
 * Created by QuanLA on 5/12/2017.
 */

public class DataUser {
    private ArrayList<UserProf> allRoom;

    public static final DataUser instance  = new DataUser();

    public void add(UserProf room){
        if(allRoom==null){
            allRoom = new ArrayList<>();
        }
        allRoom.add(room);
    }

    public ArrayList<UserProf> getAllRoom(){
        if(allRoom==null){
            allRoom = new ArrayList<>();
        }
        return allRoom;
    }

    public void clear(){
        allRoom = new ArrayList<>();
    }
}
