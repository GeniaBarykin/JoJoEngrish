package com.evgeny.app.jojoengrish;

import android.os.Bundle;

import com.evgeny.app.jojoengrish.adapters.RecyclerViewAdapter;
import com.evgeny.app.jojoengrish.api.DbHelper;
import com.evgeny.app.jojoengrish.api.SoundsTableFeeder;
import com.evgeny.app.jojoengrish.api.TagsTableFeeder;
import com.evgeny.app.jojoengrish.audio.Player;
import com.evgeny.app.jojoengrish.models.SoundModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private  ImageView searchImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDb();
        initialiseRecyclerView();
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
                    searchBar.bringToFront();
                } else {
                    showSearch=false;
                    searchBar.setVisibility(View.GONE);
                    searchImage.setVisibility(View.GONE);
                }
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);
    }

    private void initializeDb(){
        db= new DbHelper(this);
        if(db.countSounds()==0){
            SoundsTableFeeder.feed(db);
        }
    }

    private void initialiseRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.soundsRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapter(this,db.getSounds());
        recyclerView.setAdapter(mAdapter);
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
            Toast.makeText(MainActivity.this, "Settings clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
