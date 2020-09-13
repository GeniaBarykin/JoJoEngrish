package com.evgeny.app.jojoengrish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.evgeny.app.jojoengrish.activities.InfoActivity;
import com.evgeny.app.jojoengrish.activities.ListActivity;
import com.evgeny.app.jojoengrish.activities.SettingsActivity;
import com.evgeny.app.jojoengrish.adapters.RecyclerViewAdapter;
import com.evgeny.app.jojoengrish.api.DbHelper;
import com.evgeny.app.jojoengrish.audio.Player;
import com.evgeny.app.jojoengrish.crash_handler.MyExceptionHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements Serializable {
    private DbHelper db;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private boolean showSearch, adsAreLoaded;
    private FloatingActionButton fab;
    private ConstraintLayout searchBar;
    private EditText searchText;
    private  ImageView searchImage;
    private static Context context;

    private List<Object> recyclerItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        context=this;
        setContentView(R.layout.activity_main);
        initializeDb();
        initializeViews();
        initialiseRecyclerView();
        Player.restartPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Player.getInstance().loadVolume(this);

    }


    private void initializeDb(){
        db = DbHelper.getDbHelper();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = settings.getString("dbVer", "0");
        Integer db_ver = Integer.parseInt(jsonString);
        //db_ver=0; //for test
        if(db_ver< 6){
            db.reset();
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("dbVer", "6");
            editor.apply();
        }
    }

    private void initialiseRecyclerView(){
        recyclerView = findViewById(R.id.soundsRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerItems.clear();
        showGroups();
    }

    private void showGroups(){
        recyclerItems = new ArrayList<>();
        recyclerItems.addAll(db.getGroups());
        mAdapter = new RecyclerViewAdapter(context,recyclerItems);
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
                    searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            if ((actionId == EditorInfo.IME_ACTION_SEARCH)) {
                                hideKeyboard();
                                ///do search
                                Intent i = new Intent(context, ListActivity.class);
                                i.putExtra("search_word", searchText.getText().toString());
                                startActivity(i);
                                return true;
                            }
                            return false;
                        }
                    });
                } else {
                    showSearch=false;
                    searchBar.setVisibility(View.GONE);
                    searchImage.setVisibility(View.GONE);
                    hideKeyboard();
                    Intent i = new Intent(context, ListActivity.class);
                    i.putExtra("search_word", searchText.getText().toString());
                    startActivity(i);
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

    public void hideKeyboard() {
        Activity activity = this;
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }




}
