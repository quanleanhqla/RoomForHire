package com.example.quanla.roomforhire.dataFake;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.dataFake.models.Photo;
import com.example.quanla.roomforhire.dataFake.models.Room;

import java.util.ArrayList;

/**
 * Created by QuanLA on 4/20/2017.
 */

public class DataPhoto {

    private ArrayList<Photo> allPhoto;
    private ArrayList<Integer> photo;

    public static final DataPhoto instance  = new DataPhoto();

    public ArrayList<Photo> getAllPhoto(){
        allPhoto = new ArrayList<>();

        allPhoto.add(new Photo(R.drawable.tro1));
        allPhoto.add(new Photo(R.drawable.tro2));
        allPhoto.add(new Photo(R.drawable.tro3));

        return allPhoto;
    }

    public ArrayList<Integer> getPhoto(){
        if(photo==null) photo = new ArrayList<>();
        photo.add(R.drawable.tro1);
        photo.add(R.drawable.tro2);
        photo.add(R.drawable.tro3);
        return photo;
    }
}
