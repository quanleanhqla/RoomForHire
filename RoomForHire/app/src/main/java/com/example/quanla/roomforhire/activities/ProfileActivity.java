package com.example.quanla.roomforhire.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quanla.roomforhire.dataFake.models.UserProf;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.quanla.roomforhire.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    ProgressDialog progressDialog;


    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("Thông tin tài khoản");
        progressDialog = new ProgressDialog(this);

        //if the user is not logged in
        //that means current user will return null


        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        tv_name = (TextView) findViewById(R.id.txt_name);
        tv_address =(TextView) findViewById(R.id.txt_address);
        tv_phone = (TextView) findViewById(R.id.txt_phone);


        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
        else {
            loadData();
            textViewUserEmail.setText("Welcome "+user.getEmail());
        }





        //displaying logged in user name


        //adding listener to button
        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mn_home){
            startActivity(new Intent(ProfileActivity.this, CoreActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadData(){
//        progressDialog.setMessage("Đang tải thông tin...");
//        progressDialog.show();
        databaseReference.child("user").child(firebaseAuth.getCurrentUser().getUid()).child("userprof").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserProf userProf = dataSnapshot.getValue(UserProf.class);
                tv_name.setText(userProf.getName());
                tv_phone.setText(userProf.getPhone());
                tv_address.setText(userProf.getAddress());
                //progressDialog.dismiss();
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
