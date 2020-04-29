package com.evgeny.app.jojoengrish.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.evgeny.app.jojoengrish.MainActivity;
import com.evgeny.app.jojoengrish.R;
import com.evgeny.app.jojoengrish.api.DbHelper;
import com.evgeny.app.jojoengrish.api.SoundsTableFeeder;

public class LoadingActivity extends AppCompatActivity {
    private static int STATIC_TIME_OUT = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        final ImageView imageView = findViewById(R.id.logoImage);
        final int angle = 0;
        final ValueAnimator animator = ValueAnimator.ofInt(angle, 720);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                imageView.setRotation((float) (Integer) animator.getAnimatedValue());
            }
        });
        animator.setDuration(STATIC_TIME_OUT);
        animator.start();
        DbHelper db= new DbHelper(this);
        if(db.countSounds()==0 && db.countSounds()<4){
            SoundsTableFeeder.feed(db);
        }
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
