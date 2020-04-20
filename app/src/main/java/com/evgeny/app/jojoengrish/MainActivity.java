package com.evgeny.app.jojoengrish;

import android.os.Bundle;

import com.evgeny.app.jojoengrish.sqlite.DbHelper;
import com.evgeny.app.jojoengrish.sqlite.SoundsTableFeeder;
import com.evgeny.app.jojoengrish.sqlite.TagsTableFeeder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        try {
            //Player.getInstance().enable();
            //ArrayList<SoundModel> searchResult= SearchEngine.findSoundFiles("yare daze");
            //Player.getInstance().play(this,R.raw.jotaroyareyaredaze);

            DbHelper db= new DbHelper(this);
            SoundsTableFeeder.feed(db);
            TagsTableFeeder.feed(db);
            System.out.println(db.getIdByTag("yare"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
