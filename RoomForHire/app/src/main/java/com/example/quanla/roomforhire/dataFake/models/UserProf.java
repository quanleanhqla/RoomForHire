package com.example.quanla.roomforhire.dataFake.models;

/**
 * Created by QuanLA on 5/11/2017.
 */

public class UserProf {
    private String UID;
    private String name;
    private String address;
    private String phone;

    public UserProf() {
    }

    public UserProf(String UID, String name, String address, String phone) {
        this.UID = UID;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserProf{" +
                "UID='" + UID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
