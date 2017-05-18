package com.example.quanla.roomforhire.fragments;



import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanla.roomforhire.R;
import com.example.quanla.roomforhire.activities.CoreActivity;
import com.example.quanla.roomforhire.activities.LoginActivity;
import com.example.quanla.roomforhire.adapters.PagerAdapter;
import com.example.quanla.roomforhire.dataFake.DataFake;
import com.example.quanla.roomforhire.dataFake.DataMark;
import com.example.quanla.roomforhire.dataFake.DataSearch;
import com.example.quanla.roomforhire.dataFake.models.Room;
import com.example.quanla.roomforhire.events.MoveToMap;
import com.example.quanla.roomforhire.events.ReplaceFragmentEvent;
import com.example.quanla.roomforhire.events.RoomEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    @BindView(R.id.vp_horizontal_ntb)
    ViewPager viewPager;
    @BindView(R.id.ntb_horizontal)
    NavigationTabBar navigationTabBar;


    final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseAuth firebaseAuth;
    private SimpleCursorAdapter cursorAdapter;
    private String suggestionSelecect;





    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        if(getActivity() instanceof CoreActivity){
            ((CoreActivity) getActivity()).getSupportActionBar().setTitle("Trang chủ");
        }

        firebaseAuth = FirebaseAuth.getInstance();

        loadData();

        final String[] from = new String[] {"nha"};
        final int[] to = new int[] {android.R.id.text1};
        cursorAdapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_1,
                null,
                from,
                to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        cursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                return false;
            }
        });



        initUI();


        return view;
    }

    private void initUI() {

        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);



        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_home_white_24px),
                        Color.parseColor("#ffffff"))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_home_white_24px))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_stars_white_24px),
                        Color.parseColor("#ffffff"))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_stars_white_24px))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_add_box_white_24px),
                        Color.parseColor("#ffffff"))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_add_box_white_24px))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_nearby),
                        Color.parseColor("#ffffff"))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_nearby))
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                if(position==0){
                    if(getActivity() instanceof CoreActivity){
                        ((CoreActivity) getActivity()).getSupportActionBar().setTitle("Trang chủ");
                    }
                }
                else if(position==1){
                    if(getActivity() instanceof CoreActivity){
                        ((CoreActivity) getActivity()).getSupportActionBar().setTitle("Yêu thích");
                    }
                }
                else if(position==2){
                    if(getActivity() instanceof CoreActivity){
                        ((CoreActivity) getActivity()).getSupportActionBar().setTitle("Đăng tin");
                    }
                }
                else {
                    if(getActivity() instanceof CoreActivity){
                        ((CoreActivity) getActivity()).getSupportActionBar().setTitle("Gần đây");
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.mn_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSuggestionsAdapter(cursorAdapter);

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                CursorAdapter c = searchView.getSuggestionsAdapter();

                Cursor cursor = (Cursor)     searchView.getSuggestionsAdapter().getItem(position);
                suggestionSelecect = cursor.getString(1);


                searchView.setQuery( suggestionSelecect,false);
                for (Room gameRoom : DataFake.instance.getAllRoom()){
                    if (gameRoom.getTitle().toLowerCase().equals(suggestionSelecect.toLowerCase())) {
                        EventBus.getDefault().postSticky(new RoomEvent(gameRoom, MoveToMap.FROMDETAIL));
                        EventBus.getDefault().post(new ReplaceFragmentEvent(new DetailRoom(), true));
                    }
                }
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                populateAdapter(newText);
                return false;
            }
        });

    }

    public void loadData(){
        DataSearch.instance.clear();
        DataFake.instance.clear();
        databaseReference.child("user").child(firebaseAuth.getCurrentUser().getUid()).child("apartment").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Room room = dataSnapshot.getValue(Room.class);
                DataFake.instance.add(room);
                DataSearch.instance.add(room.getTitle());

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

        databaseReference.child("user").child(firebaseAuth.getCurrentUser().getUid()).child("room").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Room room = dataSnapshot.getValue(Room.class);
                DataFake.instance.add(room);
                DataSearch.instance.add(room.getTitle());
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

        databaseReference.child("user").child(firebaseAuth.getCurrentUser().getUid()).child("villa").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Room room = dataSnapshot.getValue(Room.class);
                DataFake.instance.add(room);
                DataSearch.instance.add(room.getTitle());
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

    private void populateAdapter(String query) {
        final MatrixCursor c = new MatrixCursor(new String[]{ BaseColumns._ID, "nha" });
        for (int i = 0; i< DataSearch.instance.getAll().size(); i++) {
            if (DataSearch.instance.getAll().get(i).toLowerCase().startsWith(query.toLowerCase()))
                c.addRow(new Object[] {i,DataSearch.instance.getAll().get(i)});


        }
        cursorAdapter.changeCursor(c);
    }



}
