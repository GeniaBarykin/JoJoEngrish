package com.evgeny.app.jojoengrish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.evgeny.app.jojoengrish.activities.InfoActivity;
import com.evgeny.app.jojoengrish.activities.SettingsActivity;
import com.evgeny.app.jojoengrish.adapters.RecyclerViewAdapter;
import com.evgeny.app.jojoengrish.api.DbHelper;
import com.evgeny.app.jojoengrish.api.SoundsTableFeeder;
import com.evgeny.app.jojoengrish.audio.Player;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    public static  int NUMBER_OF_ADS = 1;
    AdLoader adLoader;
    private List<Object> recyclerItems = new ArrayList<>();
    private List<UnifiedNativeAd> nativeAdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_main);

        initializeDb();

        initializeViews();
        initializeAdv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Player.getInstance().loadVolume(this);
        initialiseRecyclerView();
    }

    private void initializeAdv(){
        MobileAds.initialize(this,getString(R.string.addmob_app_id));
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
        loadNativeAds();
        recyclerItems.addAll(db.getSounds());
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
                        public boolean onEditorAction(TextView v, int keyCode, KeyEvent event) {
                            if (event!=null && event.getAction() == EditorInfo.IME_ACTION_SEARCH) {
                                hideKeyboard();
                                return true;
                            }
                            return false;
                        }
                    });
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
                            recyclerItems = new ArrayList<>();
                            recyclerItems.addAll(SearchEngine.findSoundFiles(text,db));
                            loadNativeAds();
                            mAdapter = new RecyclerViewAdapter(context,recyclerItems
                                    );
                            recyclerView.setAdapter(mAdapter);
                        }
                    });
                } else {
                    showSearch=false;
                    searchBar.setVisibility(View.GONE);
                    searchImage.setVisibility(View.GONE);
                    hideKeyboard();
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
                if(!adLoader.isLoading()){
                    insertAds();
                }
            }
        }).withAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                if(!adLoader.isLoading()){
                    insertAds();
                }
            }
        }).build();
        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
    }

    private void insertAds(){
        if(nativeAdList.size()<0){
            return;
        }
        int offset = (recyclerItems.size() / nativeAdList.size()+1);
        int index = 0;
        for(UnifiedNativeAd ad:nativeAdList){
            recyclerItems.add(ad);
        }
    }
}
