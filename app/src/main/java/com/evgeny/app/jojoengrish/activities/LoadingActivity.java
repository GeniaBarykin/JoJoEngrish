package com.evgeny.app.jojoengrish.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.evgeny.app.jojoengrish.MainActivity;
import com.evgeny.app.jojoengrish.R;

public class LoadingActivity extends AppCompatActivity {
    private static int STATIC_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
