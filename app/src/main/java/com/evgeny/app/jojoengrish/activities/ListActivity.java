package com.evgeny.app.jojoengrish.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evgeny.app.jojoengrish.R;
import com.evgeny.app.jojoengrish.adapters.RecyclerViewAdapter;
import com.evgeny.app.jojoengrish.api.DbHelper;
import com.evgeny.app.jojoengrish.api.Files;
import com.evgeny.app.jojoengrish.audio.SoundSaver;
import com.evgeny.app.jojoengrish.crash_handler.MyExceptionHandler;
import com.evgeny.app.jojoengrish.models.SoundModel;
import com.evgeny.app.jojoengrish.search_engine.SearchEngine;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements Serializable {
    private DbHelper db;

    public static  int NUMBER_OF_ADS = 10;
    public static  int NUMBER_BETWEEN_ADS = 5;
    public static  int LAST_SEEN = 0;
    private static int minAdPosition = 4;
    private static String ad_unit_id;
    static AdLoader adLoader;
    private static List<UnifiedNativeAd> nativeAdList = new ArrayList<>();
    private static boolean adsAreLoaded;
    private boolean insideTheGroup;
    private boolean showSearch;


    private String keyString;
    private List<Object> recyclerItems = new ArrayList<>();
    private String savedText;

    private ConstraintLayout searchBar;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView recyclerView;
    private EditText searchText;
    private FloatingActionButton fab;
    private  ImageView searchImage, returnButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this));

        Bundle extras = getIntent().getExtras();

        recyclerItems.clear();
        if(extras == null) {
            keyString= null;
        } else {
            keyString= extras.getString("group_name");
            if(keyString==null){
                keyString= extras.getString("search_word");
            } else {
                insideTheGroup=true;
            }
        }
        db= DbHelper.getDbHelper();
        initializeViews();
        initializeAdv();
        recyclerView = findViewById(R.id.soundsRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        changeListAdapter(keyString);
        if(!adsAreLoaded) {
            loadNativeAds();
        }
    }

    private void initializeViews(){
        setContentView(R.layout.activity_list);
        returnButton=findViewById(R.id.imageViewReturn);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchText = findViewById(R.id.searchEditText);
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
                                doSearch();
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
                    ///do search
                }
            }
        });


    }

    public void changeListAdapter(String key_string){
        recyclerItems = new ArrayList<>();
        List<SoundModel> sounds = new ArrayList<>();
        if(!adsAreLoaded){
            loadNativeAds();
        }
        if(insideTheGroup){
            sounds.addAll(db.getSoundsFromGroup(key_string));
        } else {
            sounds.addAll(SearchEngine.findSoundFiles(key_string,db));
        }
        insertAds(sounds);

        mAdapter = new RecyclerViewAdapter(this,recyclerItems, this);
        recyclerView.setAdapter(mAdapter);
    }

    private void insertAds(List<SoundModel> sounds){
        if(sounds.size()>minAdPosition){
            for (int i = 0; i < sounds.size(); i++) {
                recyclerItems.add(sounds.get(i));
                if(nativeAdList.size()>0 && i%NUMBER_BETWEEN_ADS==0 && i>=NUMBER_BETWEEN_ADS) {
                    insertAdv();
                }
            }
        } else {
            recyclerItems.addAll(sounds);
            if(nativeAdList.size()>0) {
                insertAdv();
            }
        }
    }



    private void initializeAdv(){
        MobileAds.initialize(this,getString(R.string.addmob_app_id));
        ad_unit_id = getString(R.string.native_ad_Unit_Id);
    }

    private void doSearch(){
        String textToSearch = searchText.getText().toString();
        if(textToSearch.equals("")){
            refillView(savedText);
        } else {
            savedText = textToSearch;
            searchText.setText("");
            refillView(textToSearch);
        }
    }

    private void refillView(String textToSearch ){
        if(!adsAreLoaded){
            loadNativeAds();
        }
        recyclerItems = new ArrayList<>();
        List<SoundModel> sounds;
        if(textToSearch == null || textToSearch.equals("pewdiepie")) {
            sounds = new ArrayList<>();
            sounds.add(Files.PEWDIE);
        } else {
            sounds = SearchEngine.findSoundFiles(textToSearch,db);
        }
        if(sounds.size()>minAdPosition){
            for (int i = 0; i < sounds.size(); i++) {
                recyclerItems.add(sounds.get(i));
                if(nativeAdList.size()>0 && i%NUMBER_BETWEEN_ADS==0 && i>=NUMBER_BETWEEN_ADS) {
                    insertAdv();
                }
            }
        } else {
            recyclerItems.addAll(sounds);
            if(nativeAdList.size()>0) {
                insertAdv();
            }
        }
        mAdapter = new RecyclerViewAdapter(this,recyclerItems, this);
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


    private void loadNativeAds(){
        AdLoader.Builder builder = new AdLoader.Builder( this, ad_unit_id);
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

    public void close(View view){
        finish();
    }


}
