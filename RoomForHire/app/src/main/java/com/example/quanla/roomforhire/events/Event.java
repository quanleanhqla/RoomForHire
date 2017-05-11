package com.example.quanla.roomforhire.events;

import com.example.quanla.roomforhire.dataFake.models.Room;

/**
 * Created by QuanLA on 5/12/2017.
 */

public class Event {
    private Room room;
    private MoveToMap moveToMap;

    public Event(Room room, MoveToMap moveToMap) {
        this.room = room;
        moveToMap = moveToMap;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
