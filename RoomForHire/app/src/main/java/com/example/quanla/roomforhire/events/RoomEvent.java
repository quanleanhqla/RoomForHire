package com.example.quanla.roomforhire.events;

import com.example.quanla.roomforhire.dataFake.models.Room;

/**
 * Created by QuanLA on 4/21/2017.
 */

public class RoomEvent {
    private Room room;
    private MoveToMap moveToMap;

    public RoomEvent(Room room) {
        this.room = room;
        moveToMap = MoveToMap.FROMDETAIL;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
