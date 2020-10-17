package com.evgeny.app.jojoengrish.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.evgeny.app.jojoengrish.R;
import com.evgeny.app.jojoengrish.crash_handler.MyExceptionHandler;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;

public class InfoActivity extends AppCompatActivity  implements Serializable {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this));
    }

    public void goBack(View view){
        finish();
    }

    public void visitUrl(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://davidproduction.jp/english/"));
        startActivity(i);
    }

    public void viewAdv(View view){
        startActivity(new Intent(InfoActivity.this, AdvActivity.class));
    }

    public void onWMZClicked(View view){
        copy(view, getText(R.string.action_info_сontent_4));
    }

    private void copy (View view, CharSequence text){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("wmz", text);
        clipboard.setPrimaryClip(clip);
        Snackbar.make(view, R.string.copied,
                Snackbar.LENGTH_SHORT)
                .show();
    }

    public void visitInsta(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/cyberbird_art/"));
        startActivity(browserIntent);
    }


}
