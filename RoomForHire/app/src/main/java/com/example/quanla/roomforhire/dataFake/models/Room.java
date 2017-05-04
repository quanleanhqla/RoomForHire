package com.example.quanla.roomforhire.dataFake.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by QuanLA on 4/13/2017.
 */

public class Room  {
    //@PrimaryKey
    private String id;
    private String detail;
    private String title;
    private String price;
    private String host;
    private String addressHost;
    private String phone;
    private double latitude;
    private double longitude;
    private String address;
    private String type;
    private String vung;
    private String state;
    private String dientich;
    private String danhmuc;

    public Room() {

    }



    public Room(String title, String host, String price, String phone, double latitude, double longitude) {
        this.host = host;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.title =title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Room(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVung() {
        return vung;
    }

    public void setVung(String vung) {
        this.vung = vung;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(String danhmuc) {
        this.danhmuc = danhmuc;
    }

    public String getDientich() {
        return dientich;
    }

    public void setDientich(String dientich) {
        this.dientich = dientich;
    }

    public String getAddressHost() {
        return addressHost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddressHost(String addressHost) {
        this.addressHost = addressHost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
