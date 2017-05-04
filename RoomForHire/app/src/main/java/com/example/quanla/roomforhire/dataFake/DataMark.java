//package com.example.quanla.roomforhire.dataFake;
//
//import android.content.Context;
//
//import com.example.quanla.roomforhire.dataFake.models.Room;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import io.realm.Realm;
//
///**
// * Created by QuanLA on 5/5/2017.
// */
//
//public class DataMark {
//    private Realm realm;
//
//    public static final DataMark instance  = new DataMark();
//
//    public void add(Room task){
//        realm.beginTransaction();
//        realm.copyToRealm(task);
//        realm.commitTransaction();
//
//    }
//
//    public void addOrUpdate(Room task){
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate(task);
//        realm.commitTransaction();
//    }
//
//    private DataMark(){}
//
//    public void InitialRealm(Context context){
//        Realm.init(context);
//        realm = Realm.getDefaultInstance();
//    }
//
//    public List<Room> getAllRoom(){
//        return realm.where(Room.class).findAll();
//    }
//
//    public void delete(Room task){
//        realm.beginTransaction();
//        realm.where(Room.class).equalTo("id", task.getId()).findFirst().deleteFromRealm();
//        realm.commitTransaction();
//    }
//    public void clearAll(){
//        realm.beginTransaction();
//        realm.deleteAll();
//        realm.commitTransaction();
//    }
//}
