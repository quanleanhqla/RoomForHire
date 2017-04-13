package com.example.quanla.roomforhire.dataFake.models;

/**
 * Created by QuanLA on 4/13/2017.
 */

public class Room {
    private String host;
    private String phone;
    private double latitude;
    private double longitude;

    public Room(String host, String phone, double latitude, double longitude) {
        this.host = host;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Room(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Room{" +
                "host='" + host + '\'' +
                ", phone='" + phone + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
