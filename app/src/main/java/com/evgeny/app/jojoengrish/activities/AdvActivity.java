package com.evgeny.app.jojoengrish.activities;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.evgeny.app.jojoengrish.MainActivity;
import com.evgeny.app.jojoengrish.R;
import com.evgeny.app.jojoengrish.crash_handler.MyExceptionHandler;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdvActivity extends Activity {
    private static int STATIC_TIME_OUT = 2500;
    private InterstitialAd adView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_adv);
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this));
        final ImageView imageView = findViewById(R.id.logoImageAdv);
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
        AdRequest adRequest = new AdRequest.Builder().build();
        adView = new InterstitialAd(this);
        adView.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        adView.loadAd(adRequest);

        if(adView.isLoading()) {
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    // Proceed to the next activity.
                    finish();
                }
                @Override
                public void onAdLoaded() {
                    displayInterstitial();
                }
            });
        } else {
            Toast.makeText(this,"Failed to load the adv",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void displayInterstitial() {
        if(adView.isLoaded()){
            adView.show();
        } else {
            Toast.makeText(this,"Failed to show the adv",Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    public void goBack(View view){
        finish();
    }
}
