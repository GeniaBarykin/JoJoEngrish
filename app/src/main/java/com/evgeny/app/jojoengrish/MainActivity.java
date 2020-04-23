package com.evgeny.app.jojoengrish;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.evgeny.app.jojoengrish.activities.InfoActivity;
import com.evgeny.app.jojoengrish.activities.SettingsActivity;
import com.evgeny.app.jojoengrish.adapters.RecyclerViewAdapter;
import com.evgeny.app.jojoengrish.api.DbHelper;
import com.evgeny.app.jojoengrish.api.SoundsTableFeeder;
import com.evgeny.app.jojoengrish.api.TagsTableFeeder;
import com.evgeny.app.jojoengrish.audio.Player;
import com.evgeny.app.jojoengrish.models.SoundModel;
import com.evgeny.app.jojoengrish.search_engine.SearchEngine;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DbHelper db;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private boolean showSearch;
    private FloatingActionButton fab;
    private ConstraintLayout searchBar;
    private EditText searchText;
    private  ImageView searchImage;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_main);
        initializeDb();
        initialiseRecyclerView();
        initializeViews();
    }

    private void initializeDb(){
        db= new DbHelper(this);
        if(db.countSounds()==0 && db.countSounds()<4){
            SoundsTableFeeder.feed(db);
        }

    }

    private void initialiseRecyclerView(){
        recyclerView = findViewById(R.id.soundsRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapter(this,db.getSounds());
        recyclerView.setAdapter(mAdapter);
    }

    private void initializeViews(){
        searchBar = findViewById(R.id.searchConst);
        searchImage = findViewById(R.id.searchImage);
        showSearch=false;
        searchBar.setVisibility(View.GONE);
        searchImage.setVisibility(View.GONE);
        fab=findViewById(R.id.fab);
        fab.bringToFront();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!showSearch){
                    showSearch=true;
                    searchBar.setVisibility(View.VISIBLE);
                    searchImage.setVisibility(View.VISIBLE);
                    searchImage.bringToFront();
                    searchBar.bringToFront();
                    searchText = findViewById(R.id.searchEditText);
                    searchText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String text = searchText.getText().toString();
                            mAdapter = new RecyclerViewAdapter(context,
                                    SearchEngine.findSoundFiles(text,db));
                            recyclerView.setAdapter(mAdapter);
                        }
                    });
                } else {
                    showSearch=false;
                    searchBar.setVisibility(View.GONE);
                    searchImage.setVisibility(View.GONE);
                    mAdapter = new RecyclerViewAdapter(context,db.getSounds());
                    recyclerView.setAdapter(mAdapter);
                }
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_info) {
            startActivity(new Intent(MainActivity.this, InfoActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
