package com.evgeny.app.jojoengrish.activities;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.evgeny.app.jojoengrish.R;
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

        final ImageView imageView = findViewById(R.id.logoImageAdv);
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
}
