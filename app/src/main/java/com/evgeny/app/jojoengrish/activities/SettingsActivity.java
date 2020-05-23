package com.evgeny.app.jojoengrish.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.evgeny.app.jojoengrish.R;
import com.evgeny.app.jojoengrish.api.DbHelper;
import com.evgeny.app.jojoengrish.api.Files;
import com.evgeny.app.jojoengrish.audio.Player;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class SettingsActivity extends AppCompatActivity {
    private SeekBar soundBar;
    private Context context;
    private DbHelper db;
    private AdView adView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        db = new DbHelper(context);
        initializeViews();
        initializeAds();
    }
    private void initializeAds(){
        adView = findViewById(R.id.av_bottom_banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
    private void initializeViews(){
        setContentView(R.layout.activity_settings);
        soundBar = findViewById(R.id.seekBarSound);
        soundBar.setMax(100);
        soundBar.setProgress(Player.getVolumeInt());
        soundBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                try {
                    if(Player.getInstance().isPlaying()){
                        Player.getInstance().stop();
                    }
                    String vol = soundBar.getProgress() + "";
                    Player.getInstance().setVolume(Integer.parseInt(vol),context);
                    Player.getInstance().play(context,db.getSoundAddress(Files.WRY_QUIET));
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
    }

    public void goBack(View view){
        finish();
    }
    public void reset(View view){
        db.reset();
        if(db.countSounds()!=0 && db.countTags()!=0){
            if(Player.getInstance().isPlaying()){
                Player.getInstance().stop();
            }
            try {
                Player.getInstance().play(context,db.getSoundAddress(Files.BITE_THE_DUST));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(this,"Database has been rebuilt",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Failed to rebuild the database. Reinstall the app, pls.",Toast.LENGTH_SHORT).show();
        }
    }

}
