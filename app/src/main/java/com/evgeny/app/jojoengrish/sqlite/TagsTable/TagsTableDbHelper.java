package com.evgeny.app.jojoengrish.sqlite.TagsTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.evgeny.app.jojoengrish.models.SoundModel;
import com.evgeny.app.jojoengrish.sqlite.soundsTable.Table1Feeder;

import java.util.ArrayList;

public class TagsTableDbHelper extends SQLiteOpenHelper {


    private final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Table2Feeder.TABLE_NAME;

    public TagsTableDbHelper(@Nullable Context context) {
        super(context,Table2Feeder.TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table2Feeder.makeContract());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }

    public boolean post(SoundModel sound){
        SQLiteDatabase db = this.getWritableDatabase();
// Create a new map of values, where column names are the keys
        for (String tag :
                sound.getTags()) {
            if(sound.getID()!=-1) {
                ContentValues values = new ContentValues();
                values.put(Table2Feeder.KEY_TAGS, sound.getName());
                values.put(Table2Feeder.KEY_ID, sound.getID());
                long newRowId = db.insert(Table1Feeder.TABLE_NAME, null, values);
                if(newRowId == -1) {
                    return false;
                }
            }
        }
        db.close();
        return true;
    }

    public void get(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query  = "SELECT * FROM " + Table1Feeder.TABLE_NAME + " WHERE name " + name;
        Cursor data = db.rawQuery(query,null);
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        data.close();
        System.out.println(listData);
    }
}