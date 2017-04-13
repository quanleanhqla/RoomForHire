package com.example.quanla.roomforhire.dataFake;

import com.example.quanla.roomforhire.dataFake.models.Room;

import java.util.ArrayList;

/**
 * Created by QuanLA on 4/13/2017.
 */

public class DataFake {
    private ArrayList<Room> allRoom;

    public static final DataFake instance  = new DataFake();

    public ArrayList<Room> getAllRoom(){
        allRoom = new ArrayList<>();
        allRoom.add(new Room("Cô Phương", "0999999999", 21.038993, 105.783112));
        allRoom.add(new Room("Chú Nguyên", "0988888888", 21.035648, 105.784678));
        allRoom.add(new Room("Chú Tín", "0977777777", 21.037379, 105.779311));
        allRoom.add(new Room("Chú Vương", "0966666666", 21.033614, 105.784418));
        allRoom.add(new Room("Chú Tân", "0955555555", 21.039672, 105.787186));

        return allRoom;
    }
}
