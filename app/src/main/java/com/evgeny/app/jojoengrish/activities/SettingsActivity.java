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
import com.evgeny.app.jojoengrish.audio.Player;

public class SettingsActivity extends AppCompatActivity {
    private SeekBar soundBar;
    private Context context;
    private DbHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        db = new DbHelper(context);
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
                Player.getInstance().setVolume(Integer.parseInt(soundBar.getProgress() + ""));
                try {
                    Player.getInstance().play(context,db.getSoundAddress(1));
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
            Toast.makeText(this,"Database has been rebuilt",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Failed to rebuild the database. Reinstall the app, pls.",Toast.LENGTH_SHORT).show();
        }
    }

}
