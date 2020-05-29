package com.evgeny.app.jojoengrish.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.evgeny.app.jojoengrish.R;

import java.io.Serializable;

public class InfoActivity extends AppCompatActivity  implements Serializable {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

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


}
