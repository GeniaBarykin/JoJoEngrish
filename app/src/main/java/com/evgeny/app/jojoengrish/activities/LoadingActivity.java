package com.evgeny.app.jojoengrish.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import com.evgeny.app.jojoengrish.MainActivity;
import com.evgeny.app.jojoengrish.R;
import com.evgeny.app.jojoengrish.api.Files;
import com.evgeny.app.jojoengrish.crash_handler.DatabaseHandler;

public class LoadingActivity extends AppCompatActivity {
    private static int STATIC_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new DatabaseHandler(this));
        setContentView(R.layout.activity_loading);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String jsonString = settings.getString("dbVer", "0");
        Integer db_ver = Integer.parseInt(jsonString);
        if(db_ver< Files.CURRENT_VER) {
            new AlertDialog.Builder(this)
                    .setTitle("Database is loading")
                    .setMessage("In order to make further launches faster database creates connections now. It takes time. Just wait!")
                    .show();
            SharedPreferences.Editor editor = settings.edit();
            String toJson = Integer.toString(Files.CURRENT_VER);
            editor.putString("dbVer", toJson);
            editor.apply();
            MainActivity.buildDB = true;
        }
        final ImageView imageView = findViewById(R.id.logoImage);
        final int angle = 0;
        final ValueAnimator animator = ValueAnimator.ofInt(angle, 720);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                imageView.setRotation((float) (Integer) animator.getAnimatedValue());
                if((Integer) animator.getAnimatedValue()==720){
                    imageView.setImageResource(R.drawable.loading_complete);
                }
            }
        });
        animator.setDuration(STATIC_TIME_OUT);
        animator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, STATIC_TIME_OUT);
    }
}
