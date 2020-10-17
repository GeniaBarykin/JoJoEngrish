package com.evgeny.app.jojoengrish.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.evgeny.app.jojoengrish.MainActivity;
import com.evgeny.app.jojoengrish.R;
import com.evgeny.app.jojoengrish.api.Files;
import com.evgeny.app.jojoengrish.crash_handler.DatabaseHandler;

public class LoadingActivity extends AppCompatActivity {
    private static int STATIC_TIME_OUT = 2500;
    private int EXTRA_TIME = 1000;
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new DatabaseHandler(this));
        setContentView(R.layout.activity_loading);
        context=this;
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = settings.getString("dbVer", "0");
        Integer db_ver = Integer.parseInt(jsonString);

        if(db_ver< Files.CURRENT_VER) {
            STATIC_TIME_OUT +=EXTRA_TIME;
            MainActivity.buildDB = true;
        } else {
            LinearLayout loadScreen= findViewById(R.id.firstLoadScreen);
            loadScreen.setVisibility(View.GONE);
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

    public static void applyDBChanges(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        String toJson = Integer.toString(Files.CURRENT_VER);
        editor.putString("dbVer", toJson);
        editor.apply();
    }
}
