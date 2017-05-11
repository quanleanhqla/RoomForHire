package com.example.quanla.roomforhire.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.dataFake.DataFake;
import com.example.quanla.roomforhire.dataFake.DataUser;
import com.example.quanla.roomforhire.dataFake.models.Room;
import com.example.quanla.roomforhire.dataFake.models.UserProf;
import com.example.quanla.roomforhire.events.ReplaceFragmentEvent;
import com.example.quanla.roomforhire.fragments.MainFragment;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CoreActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth firebaseAuth;
    final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        firebaseAuth = FirebaseAuth.getInstance();


        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(CoreActivity.this, LoginActivity.class));
        }
        else{
            loadData();
            mainFragment = new MainFragment();
            replaceFragment(mainFragment, false);
        }


        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getSupportFragmentManager().getBackStackEntryCount() > 0){
                    toggle.setDrawerIndicatorEnabled(false);
                    toggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24px);
                    toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });
                }
                else{
                    toggle.setDrawerIndicatorEnabled(true);
                    toggle.setToolbarNavigationClickListener(null);
                }
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        EventBus.getDefault().register(this);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.core, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.nav_account){
            if(firebaseAuth.getCurrentUser()==null){
                finish();
                startActivity(new Intent(CoreActivity.this, LoginActivity.class));

            }
            else{
                finish();
                startActivity(new Intent(CoreActivity.this, ProfileActivity.class));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void replaceFragment(ReplaceFragmentEvent fragmentReplaceEvent){
        replaceFragment(fragmentReplaceEvent.getFragment(), fragmentReplaceEvent.isAddToBackStack());
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack){
        if(addToBackStack) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl, fragment)
                    .addToBackStack(null)
                    .commit();
        }
        else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl, fragment)
                    .commit();
        }
    }

    public void loadData(){
        DataFake.instance.clear();
        databaseReference.child("apartment").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Room room = dataSnapshot.getValue(Room.class);
                databaseReference.child("user").child(firebaseAuth.getCurrentUser().getUid()).child("apartment").child(room.getTitle()).setValue(room);
                DataFake.instance.add(room);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("villa").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Room room = dataSnapshot.getValue(Room.class);
                databaseReference.child("user").child(firebaseAuth.getCurrentUser().getUid()).child("villa").child(room.getTitle()).setValue(room);
                DataFake.instance.add(room);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("room").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Room room = dataSnapshot.getValue(Room.class);
                databaseReference.child("user").child(firebaseAuth.getCurrentUser().getUid()).child("room").child(room.getTitle()).setValue(room);
                DataFake.instance.add(room);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("user").child(firebaseAuth.getCurrentUser().getUid()).child("userprof").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserProf userProf = dataSnapshot.getValue(UserProf.class);
                DataUser.instance.add(userProf);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
