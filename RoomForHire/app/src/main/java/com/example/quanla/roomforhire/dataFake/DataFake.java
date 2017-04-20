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
        allRoom.add(new Room("Phòng cho thuê 12m2" ,"Cô Phương","1.300.000 đồng", "0999999999", 21.038993, 105.783112));
        allRoom.add(new Room("Phòng trọ tầng 1 20m2" ,"Chú Nguyên","1.300.000 đồng", "0988888888", 21.035648, 105.784678));
        allRoom.add(new Room("Phòng cho thuê","Chú Tín","1.300.000 đồng", "0977777777", 21.037379, 105.779311));
        allRoom.add(new Room("Có phòng cho thuê","Chú Vương","1.300.000 đồng", "0966666666", 21.033614, 105.784418));
        allRoom.add(new Room("Phòng cho thuê 15m2","Chú Tân","1.300.000 đồng", "0955555555", 21.039672, 105.787186));

        return allRoom;
    }
}
