package com.evgeny.app.jojoengrish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.evgeny.app.jojoengrish.activities.InfoActivity;
import com.evgeny.app.jojoengrish.activities.SettingsActivity;
import com.evgeny.app.jojoengrish.adapters.RecyclerViewAdapter;
import com.evgeny.app.jojoengrish.api.DbHelper;
import com.evgeny.app.jojoengrish.audio.Player;
import com.evgeny.app.jojoengrish.crash_handler.MyExceptionHandler;
import com.evgeny.app.jojoengrish.models.SoundModel;
import com.evgeny.app.jojoengrish.search_engine.SearchEngine;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
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
    private Context context;
    public static  int NUMBER_OF_ADS = 10;
    public static  int NUMBER_BETWEEN_ADS = 5;
    public static  int LAST_SEEN = 0;
    private int minAdPosition = 4;
    AdLoader adLoader;
    private List<Object> recyclerItems = new ArrayList<>();
    private List<UnifiedNativeAd> nativeAdList = new ArrayList<>();
    private boolean order = false;
    private String textToSearch = "";

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

        initializeAdv();
        initializeDb();
        initializeViews();
        initialiseRecyclerView();
        Player.restartPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!adsAreLoaded){
            loadNativeAds();
        }
        Player.getInstance().loadVolume(this);
    }

    private void initializeAdv(){
        MobileAds.initialize(this,getString(R.string.addmob_app_id));
    }


    private void initializeDb(){
        db = DbHelper.getDbHelper();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = settings.getString("dbVer", "0");
        Integer db_ver = Integer.parseInt(jsonString);
        if(db_ver< 2){
            db.reset();
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("dbVer", "2");
            editor.apply();
        }
    }

    private void initialiseRecyclerView(){
        recyclerView = findViewById(R.id.soundsRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerItems.clear();
        loadNativeAds();
        recyclerItems.addAll(db.getSounds());
        if(recyclerItems.size()==0){
            recyclerItems.add(SoundModel.errorModel());
        }
        mAdapter = new RecyclerViewAdapter(this,recyclerItems);
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
                                doSearch();
                                hideKeyboard();
                                return true;
                            }
                            return false;
                        }
                    });
                } else {
                    showSearch=false;
                    searchBar.setVisibility(View.GONE);
                    searchImage.setVisibility(View.GONE);
                    doSearch();
                    hideKeyboard();
                }
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);
    }

    private void doSearch(){
        textToSearch = searchText.getText().toString();
        refillView();
    }

    private void refillView(){
        if(!adsAreLoaded){
            loadNativeAds();
        }
        recyclerItems = new ArrayList<>();
        List<SoundModel> sounds = SearchEngine.findSoundFiles(textToSearch,db, order);
        if(sounds.size()>minAdPosition){
            for (int i = 0; i < sounds.size(); i++) {
                recyclerItems.add(sounds.get(i));
                if(nativeAdList.size()>0 && i%NUMBER_BETWEEN_ADS==0 && i>4) {
                    insertAdv();
                }
            }
        } else {
            recyclerItems.addAll(sounds);
            if(nativeAdList.size()>0) {
                insertAdv();
            }
        }
        mAdapter = new RecyclerViewAdapter(context,recyclerItems);
        recyclerView.setAdapter(mAdapter);
    }

    private void insertAdv(){
        recyclerItems.add(nativeAdList.get(LAST_SEEN));
        if (nativeAdList.size() > LAST_SEEN + 1) {
            LAST_SEEN++;
        } else {
            LAST_SEEN = 0;
        }
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

    private void loadNativeAds(){
        AdLoader.Builder builder = new AdLoader.Builder(this, getString(R.string.native_ad_Unit_Id));
        adLoader = builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                nativeAdList.add(unifiedNativeAd);
            }
        }).withAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }
        }).build();
        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
        if(nativeAdList.size()>=NUMBER_OF_ADS){
            adsAreLoaded=true;
        }
    }

    public void toggleSort(View view){
        FloatingActionButton sorting = findViewById(R.id.abc);
        if(order){
            order=false;
            sorting.setImageResource(R.drawable.icon_sort_cancel);
        } else {
            order=true;
            sorting.setImageResource(R.drawable.icon_sort);
        }
        refillView();
    }

}
